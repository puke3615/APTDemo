package com.puke.aptdeom;


import com.puke.PK;
import com.puke.Util;
import com.puke.debug.DebugActivity;

@PK("SetupActivity注解信息..")
public class SetupActivity extends DebugActivity {

    @Debug
    public void 测试APT() {
        Util util = new Util();
        T(util);
    }

}
