package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.moveobj.IObject;

public interface IGameObjCmd {

    void execute(IObject context);
}
