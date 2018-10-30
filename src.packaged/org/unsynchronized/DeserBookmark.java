package org.unsynchronized;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class DeserBookmark {
	static Map<String, String> displayNames = new HashMap<>();
	static {
		displayNames.put("m_className", "column class name");
		displayNames.put("m_sortAscending", "sort ascending");
		displayNames.put("m_sortAscending", "sort ascending");
		displayNames.put("m_sortOrder", "sort order");
		displayNames.put("m_groupingActive", "grouping active");
	}
	public static void main(String[] args) throws IOException {
		String filename = "C:\\Users\\fam\\Desktop\\dbeaver_export\\frank_A_mit_Sortierung.jo";
        FileInputStream fis = new FileInputStream(filename);
		jdeserialize jd = new jdeserialize(filename);

//        jd.debugEnabled = false;
	    jd.run(fis, false);
	    List<Map<Integer, content>> handleMaps = jd.getHandleMaps();
	    for (Map<Integer, content> handleMap: handleMaps) {
	    	for (Entry<Integer, content> handleEntry: handleMap.entrySet()) {
	    		content curContent = handleEntry.getValue();
//	    		System.out.println(curContent.getType());
//	    		System.out.println(curContent.toString());
	    		if (curContent instanceof instance) {
	    			instance curInstance = (instance) curContent;
	    			if ("com.bsiag.crm.shared.core.bookmarks.CoreTablePageState".equals(curInstance.classdesc.name) ||
	    					"org.eclipse.scout.rt.shared.services.common.bookmark.TableColumnState".equals(curInstance.classdesc.name)) {
    					System.out.println("found instance: " + curInstance.toString());
	    				for(Entry<classdesc, Map<field, Object>> fieldData: curInstance.fielddata.entrySet()) {
	    					System.out.println(fieldData.getKey());
	    					Map<field, Object> fieldMap = fieldData.getValue();
    						for (field f: curInstance.classdesc.fields) {
    							String displayName = displayNames.get(f.name);
    							if (displayName == null) {
    								displayName = "not displayed: " + f.name;
    							}
    							if (displayName != null) {
    								System.out.println("name: " + displayName);
    								//System.out.println("java type: " + f.getJavaType());
    								Object object = fieldMap.get(f);
    								if (object instanceof stringobj) {
    									stringobj string = (stringobj) object;
    									System.out.println("string value: " + string.value);
    								} else {
    									System.out.println("value : " + object);
    								}
    							}
	    					}
	    				}
	    			}
	    		}
	    	}
	    }
	}
}
