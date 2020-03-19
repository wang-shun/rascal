module lang::xml::IO

@javaClass{org.rascalmpl.library.lang.xml.IO}
java node readXML(loc file, bool trim = true, bool fullyQualify = false);

@javaClass{org.rascalmpl.library.lang.xml.IO}
java node readXML(str contents, bool trim = true, bool fullyQualify = false);

test bool nestedElementTest() {
  example = "\<aap\>\<noot\>mies\</noot\>\</aap\>";
  
  val = readXML(example);
  
  return val == "aap"(["noot"(["mies"])]);
}
  
test bool attributeTest() {
  example = "\<aap age=\"1\"\>\</aap\>";
  
  val = readXML(example);
  
  return val == "aap"([], age="1");
}