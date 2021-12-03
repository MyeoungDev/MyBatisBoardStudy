# Java Collection

Java에서 컬렉션(Collection)이란 데이터의 집합, 그룹을 의미한다.

JCF(Java Collections Framework)는 이러한 데이터, 자료구조인 컬렉션과 이를 구현하는 인터페이스를 제공한다.

![이미지](https://t1.daumcdn.net/cfile/tistory/99B88F3E5AC70FB419)

<br>

---

## Collections Framework 주요 인터페이스

| 인터페이스 | 설명 | 구현 클래스 |
| --- | --- | --- |
| ```List<E>``` | 순서가 있는 데이터의 집합으로, 데이터의 중복을 허용함. | LinkedList Stack Vector ArrayList |
| ```Set<E>``` |  순서가 없는 데이터의 집합으로, 데이터의 중복을 허용하지 않는다. | HashSet SortedSet |
| ```Map<K,V>``` | 키와 값의 한쌍으로 이루어지는 데이터의 집합으로 순서가 없고, 키는 중복을 허용하지 않지만, 값은 중복될 수 있다. | HastTable HashMap SortedMap |

이 중 List와 Set 인터페이스는 모두 Collection 인터페이스를 상속받지만,
<br>
구조상의 차이로 인해 Map 인터페이스는 별도로 정의된다. 그렇지만 모두 Collcetion으로 분류된다.


## Collection 주요 메소드

| 리턴타입 | 메소드 | 설명 |
| --- | --- | --- |
| boolean | add(E e) | 요소를 추가한다 |
| boolean | addAll(Collection) | 매개 변수로 넘어온 컬렉션의 모든 요소를 추가한다. | 
| void | clear() | 컬렉션에 있는 모든 데이터를 지운다. |
| boolean | contains(Object) | 매개 변수로 넘어온 객체가 해당 컬랙션에 있는지 확인한다. <br> 동일한 값이 존재하면 true를 반환. |
| boolean | containsAll(Collect) | 매개 변수로 넘어온 객체들이 해당 컬렉션에 있는지 확인한다. <br> 매개 변수로 넘어온 컬렉션에 있는 요소들과 동일한 값들이 모두 있으면 true를 반환. |
| boolean | equals(Object) | 매개 변수로 넘어온 객체와 같은 객체인지 확인한다. |
| int | hasCode() | 해시 코드 값을 리턴한다. | 
| boolean | isEmpty() | 컬렉션이 비어있는지 확인한다. <br> 비어 있으면 true를 반환한다. |
| Iterator | iterator() | 데이터를 한 건씩 처리하기 위한 iterator 객체를 리턴한다. |
| boolean | remove(Object) | 매개 변수와 동일한 객체를 삭제한다. |
| boolean | removeAll(Collection) | 매개 변수로 넘어온 객체들을 해당 컬렉션에서 삭제한다. |
| boolean | retainAll(Collection) | 매개 변수로 넘어온 객체들만을 컬렉션에 남겨둔다. |
| int | size() | 요소의 개수를 리턴한다. |
| Object[] | toArray() | 컬렉션에 있는 데이터들을 배열로 복사한다. |
| ```<T>T[]``` | toArray(T[]) | 컬렉션에 있는 데이터들을 지정한 타입의 배열로 복사한다. |

---


## Map 컬렉션
Map 인터페이스는 Collection 인터페이스와 다른 저장 방식을 갖는다.
<br>
Map 인터페이스를 구현한 Map 컬렉션 클래스들은 키와 값을 하나의 쌍으로 저장하는 방식(key-value)을 사용한다.

### Map 컬렉션 특징과 대표 클래스

- 요소의 저장 순서를 유지하지 않는다.
- 키는 중복을 허용하지 않지만, 값은 중복을 허용한다.

**HsahMap<K,V> Hashtable<K,V> TreeMap<K,V>**
  
**HashMap<K,V>** 클래스는 Map 컬렉션 클래스에서 가장 많이 사용되는 클래스 중 하나이다.
<br>
필자는 그렇기에 HashMap<K,V>만을 다룰 것이다.
<br>

참고로
```java
Map<String, String> map = new HashMap<>();
```
map 선언시 이렇게 사용하는 이유는 Map이 인터페이스이기 때문이다.
<br>
즉, 인터페이스여서 선언만 가능하며 객체 생성이 불가능하다.
<br>
때문에 자식인 HashMap으로 객체를 생성한다.

### HashMap<K,V> 예제

```java
import java.util.HashMap;
import java.util.Map;
public class Example{
    public static void HashMapExample (String[] arg) {
        Map<String, Integer> map = new HashMap<>();
        
        map.put("key1", 100);
        map.put("key2", 200);
        map.put("key3", 300);

        System.out.println(map);    // {key1=100, key2=200, key3=300}
        System.out.println(map.get("key1"));    // 100
        System.out.println(map.get("key2"));    // 200
        System.out.println(map.get("key2"));    // 300

        if (!map.containsKey("key2")) {     // 키가 들어있는지 확인, 있으면 덮어쓰지 않는다.
            map.put("key2", 300);
        }
        System.out.println(map.get("key2"));    // 200

        System.out.println("모든 Key-Value 조회");
        for (String key : map.keySet()) {
            System.out.println("{" + key + "," + map.get(key) + "}");
        }
        /*결과*/
        // 모든 Key-Value 조회
        // {key1,100}
        // {key2,200}
        // {key3,300}
        
        // forEach문 사용으로 조회
        map.forEach((key, value) ->
        {
            System.out.println("{" + key + "," + map.get(key) + "}");
        }
        );
        // {key1,100}
        // {key2,100}
        // {key3,100}

        /* replace를 이용하여 수정 */
        map.replace("key2", 400);
        System.out.println("key2 =" + map.get("key2"));
        // 400
        
        /* remove 제거 */
        map.remove("key1");

        /* 맵의 크기 */
        System.out.println("map size = " + map.size());
        // 2
    }
}
```

### Hash<K,V> 주요 메소드


| 리턴타입 | 메소드 | 설명 |
| --- | --- | --- |
| void | clear() | 해당 맵의 모든 매핑을 제거함 |
| boolean | containsKey(Object key) | 해당 맵이 전달된 키를 포함하고 있는지 확인 |
| boolean | containsValue(Object value) | 해당 맵이 전달된 값에 해당하는 하나 이상의 value를 포함하고 있는지 확인 |
| void | get(Object Key) | 해당 맵에서 전달된 키에 대응하는 값을 반환함. <br> 만약 해당 맵이 전달된 키를 포함한 매핑을 포함하고 있지 않으면 null을 반환. |
| boolean | isEmpty() | 해당 맵이 비어 있는지를 확인. |
| Set<K> | keySet() | 해당 맵에 포함되어 있는 모든 키로 만들어진 Set객체를 반환. |
| void | put(K key, V value) | 해당 맵에 전달된 키에 대응하는 값으로 특정 값을 매핑함. |
| void | remove(Object key) | 해당 맵에서 전달된 키에 대응하는 매핑을 제거함. |
| boolean | remove(Object key, Object value) | 해당 맵에서 특정 값에 대응하는 키의 매핑을 제거함. |
| void | replace(K key, V value) | 해당 맵에서 전달된 키에 대응하는 값을 특정 값으로 대체함. |
| boolean | replace(K key, V oldValue, V newValue) | 해당 맵에서 특정 값에 대응하는 전달된 키의 값을 새로운 값으로 대체함. |
| int | size() | 해당 맵의 매핑의 총 개수를 반환함. |









