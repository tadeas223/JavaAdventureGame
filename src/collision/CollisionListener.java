package collision;

import gameObject.GameObject;

public interface CollisionListener {
    public void onCollisionEnter(GameObject gameObject);
}
