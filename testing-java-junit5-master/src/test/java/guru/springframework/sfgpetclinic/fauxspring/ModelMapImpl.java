package guru.springframework.sfgpetclinic.fauxspring;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rishi on 10/09/2021
 */
public class ModelMapImpl implements Model {

    public Map<String, Object> getMap() {
        return map;
    }

    private final Map<String, Object> map = new HashMap<>();

    @Override
    public void addAttribute(String key, Object o) {
        map.put(key, o);
    }

    @Override
    public void addAttribute(Object o) {
        // do nothing
    }
}
