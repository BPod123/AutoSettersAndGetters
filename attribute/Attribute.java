import AccessType;

/**
 * @author Ben Podrazhansky
 */
public class Attribute {
    /** The name of the attribute */
    private String name;
    /** The access type: public, private, protected, or default */
    private AccessType accessType;
    /** The modifier type: static, final, or default */
    private ModifierType modifierType;
    /** The type: object or native type */
    private String type;
    /** A string with the correct amount of spaces to indent properly */
    private String indent;

    /**
     * @param string The String with the javadoc and attribute info for this
     *               attribute
     */
    public Attribute(String str) {
        if (str.indexOf("/*") >= 0 && str.indexOf("*/") < str.indexOf("/*")) {
            String deleteString = str.substring(str.indexOf("/*"), str.indexOf("*/") + 1);
            str = str.replace(deleteString, "");
        }
        String[] strings = str.split("\n");
        str = strings[strings.length - 1];
        // Access Modifier
        if (str.indexOf("public") >= 0) {
            accessType = AccessType.PUBLIC;
            str = str.replace("public", "");
        } else if (string.indexOf("private") >= 0) {
            accessType = AccessType.PRIVATE;

        } else if (str.indexOf("protected") >= 0) {
            accessType = AccessType.PROTECTED;
            str = str.replace("protected", "");
        } else {
            accessType = AccessType.DEFAULT;
        }
        // Deal with initializations
        if (str.indexOf("=") > 0) {
            str = str.substring(0, str.indexOf("="));
        }
        // Modifiers
        if (str.indexOf("static") >= 0 && str.indexOf("final") >= 0) {
            if (str.indexOf("static") > str.indexOf("final")) {
                modifierType = ModifierType.STATICFINAL;
            } else {
                modifierType = ModifierType.FINALSTATIC;
            }
            str = str.replace("static", "");
            str = str.replace("final", "");
        } else if (str.indexOf("static") >= 0) {
            modifierType = ModifierType.STATIC;
            str = str.replace("static", "");
        } else if (str.indexOf("final") >= 0) {
            modifierType = ModifierType.FINAL;
            str = str.replace("final", "");
        } else {
            modifierType = ModifierType.DEFAULT;
        }
        str = str.trim();
        if (str.indexOf("<") > 0) {
            type = str.substring(0, str.indexOf(">") + 1);
            str.replace(type, "");
        }
        str = str.trim();
        name = str;
    }

    public String getGetter() {
        if (accessType == AccessType.PUBLIC) {
            throw new Exception("Public attributes don't need getters");
        }
        String res = indent + "/**@return ".concat(name).concat(" */\n" + indent + "public ");
        String capitalName = name.substring(0, 1).toUpperCase() + name.substring(1);
        switch (modifierType) {
        case FINAL:
            res += "final ";
            break;
        case STATIC:
            res += "static ";
            break;
        case FINALSTATIC:
            res += "final static";
            break;
        case STATICFINAL:
            res += "static final ";
            break;
        default:
            break;
        }
        res += type + " get" + capitalName + "() { \n" + indent + "\treturn " + name + ";\n" + indent + "}\n";
        return res;
    }

    public String getSetter() {
        if (accessType == AccessType.PUBLIC) {
            throw new Exception("Public variables don't need setters.");
        }
        String res = indent + "/**@return ".concat(name).concat(" */\n" + indent + "public ");
        String capitalName = name.substring(0, 1).toUpperCase() + name.substring(1);
        switch (modifierType) {
        case FINAL:
            res += "final ";
            break;
        case STATIC:
            res += "static ";
            break;
        case FINALSTATIC:
            res += "final static";
            break;
        case STATICFINAL:
            res += "static final ";
            break;
        default:
            break;
        }
        res += type + " get" + capitalName + "() { \n" + indent + "\treturn " + name + ";\n" + indent + "}\n";
        return res;
    }
}
