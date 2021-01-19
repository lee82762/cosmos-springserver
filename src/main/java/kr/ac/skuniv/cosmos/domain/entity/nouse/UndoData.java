package kr.ac.skuniv.cosmos.domain.entity.nouse;

import java.util.List;

public class UndoData {

    public int SelectionStart;
    public int SelectionLength;
    public List<KData> SelectedData;
    public int UndoState = 0;       //None : 0, Insert : 1,  Delete : 2

}
