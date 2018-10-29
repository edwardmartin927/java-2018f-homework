package calabashBrothers.beings;

import calabashBrothers.Maps;
import calabashBrothers.beings.enums.CreatureType;

/**
 * @ Author     ：Young
 * @ Description：蝎子精
 */
public class Scorpion extends Monster implements Fighting{
    public Scorpion() {
        this.type=CreatureType.MONSTER_LEADER;
        this.name="蝎子精";
    }

    @Override
    public void selfIntroduction() {
        super.selfIntroduction();
        System.out.println("我是"+this.name);
    }

    @Override
    public void Fighting(Maps maps, int x, int y) {
        //TODO
    }
}
