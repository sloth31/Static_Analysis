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

package pascal.taie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Main class for assignments.
 */
public class Assignment {

    public static void main(String[] args) {//主程序
        //使用： -cp <CLASS_PATH> -m <CLASS_NAME>
        //<CLASS_PATH> 是 .class 文件所在文件夹的路径，<CLASS_NAME> 是待分析类的类名
        if (args.length > 0) {
            List<String> argList = new ArrayList<>();
            Collections.addAll(argList, "-pp", "-p", "plan.yml");
            Collections.addAll(argList, args);
            Main.main(argList.toArray(new String[0]));
        } else {
            System.out.println("Usage: -cp <CLASS_PATH> -m <CLASS_NAME>");
        }
    }
}
