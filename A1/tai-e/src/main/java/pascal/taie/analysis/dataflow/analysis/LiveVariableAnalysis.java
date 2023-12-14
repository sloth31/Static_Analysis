/*
 * Tai-e: A Static Analysis Framework for Java
 *
 * Copyright (C) 2022 Tian Tan <tiantan@nju.edu.cn>
 * Copyright (C) 2022 Yue Li <yueli@nju.edu.cn>
 *
 * This file is part of Tai-e.
 *
 * Tai-e is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 *
 * Tai-e is distributed in the hope that it will be useful,but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General
 * Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Tai-e. If not, see <https://www.gnu.org/licenses/>.
 */

package pascal.taie.analysis.dataflow.analysis;

import pascal.taie.analysis.dataflow.fact.DataflowResult;
import pascal.taie.analysis.dataflow.fact.SetFact;
import pascal.taie.analysis.graph.cfg.CFG;
import pascal.taie.config.AnalysisConfig;
import pascal.taie.ir.exp.LValue;
import pascal.taie.ir.exp.RValue;
import pascal.taie.ir.exp.Var;
import pascal.taie.ir.stmt.Stmt;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of classic live variable analysis.
 */
public class LiveVariableAnalysis extends
        AbstractDataflowAnalysis<Stmt, SetFact<Var>> {

    public static final String ID = "livevar";

    public LiveVariableAnalysis(AnalysisConfig config) {
        super(config);
    }

    @Override
    public boolean isForward() {
        return false;
    }

    @Override
    public SetFact<Var> newBoundaryFact(CFG<Stmt> cfg) {
        // TODO - finish me
        SetFact<Var> res=new SetFact<Var>();
        //初始化Exit的IN为空
        return res;
    }

    @Override
    public SetFact<Var> newInitialFact() {
        // TODO - finish me
        SetFact<Var> res=new SetFact<Var>();
        return res;
    }

    @Override
    public void meetInto(SetFact<Var> fact, SetFact<Var> target) {
        // TODO - finish me
        target.union(fact);
    }

    @Override
    //更新当前块的IN
    public boolean transferNode(Stmt stmt, SetFact<Var> in, SetFact<Var> out) {
        // TODO - finish me

        LValue lValue;
        SetFact<Var> in_new=new SetFact<>();
        in_new.set(out);//备份原来的out
        if(stmt.getDef().isPresent())//不为null的情况
        {Optional<LValue> _def = stmt.getDef();//获取定义
        lValue = _def.get();
        if(lValue instanceof Var)
        {in_new.remove((Var) lValue);}//删除已定义变量
        }
        List<RValue> uses = stmt.getUses();
        for(RValue rValue:uses)
        {
            if(rValue instanceof Var)
            {in_new.add((Var)rValue);}
        }

        boolean flag=!in_new.equals(in);//当两次的IN集合相同时，算法结束
        in.set(in_new);
        return flag;//算法结束时返回false
    }
}
