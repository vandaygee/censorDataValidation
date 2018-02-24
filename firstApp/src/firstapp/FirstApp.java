
package firstapp;

import java.io.File;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.*;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException

import org.json.*;
import org.json.simple.*;
//import org.json.simple.JSONObject;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.time.Duration;
import java.time.Instant;

import java.util.Iterator;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.vocabulary.VCARD;
import org.apache.jena.vocabulary.*;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.RDFWriter;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;
import org.apache.jena.sparql.core.Var;


import org.apache.jena.datatypes.RDFDatatype;
import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.util.FileManager;
import org.json.simple.parser.JSONParser;


public class FirstApp {
    //private static final String BASE="http://SmartHO.org.com/";
    private static final String BASE="http://www.semanticweb.org/40011133/ontologies/2017/10/untitled-ontology-21#";
    public static String ns = "http://www.semanticweb.org/40011133/ontologies/2017/10/untitled-ontology-21#"; 
    public static String tempNS = "http://www.semanticweb.org/40011133/ontologies/2017/10/untitled-ontology-21#temperature";
    public static String foafNS = "http://xmlns.com/foaf/0.1#"; 
    public static String foafhasTimesatmp = foafNS+"hasTimestamp"; 
    public static String foafHasValue = foafNS+"hasValue"; 
    public static String stringTypeURI = "http://www.w3.org/2001/XMLSchema#string"; 

  public static void main(String[] args) {
    try {
        
//        OWLOntologyManager man = OWLManager.createOWLOntologyManager();
//        File file = new File("C:\\Users\\Duchess\\Documents\\SmartSUM\\smartSpace.owl");
//        OWLOntology o = man.loadOntologyFromOntologyDocument(file);
//        System.out.println(o);
//          Object obj = parser.parse(new FileReader("C:\\Users\\Duchess\\Documents\\SmartSUM\\Dataset\\raw_weather_data_aarhus\\tempm.txt"));
//          JSONObject outerObject = new JSONObject(obj);
          
          String path="C:\\Users\\Duchess\\Documents\\SmartSUM\\Dataset\\raw_weather_data_aarhus\\tempm5.txt";
          //String path="C:\\Users\\Duchess\\Documents\\SmartSUM\\Dataset\\temp.json";
          //String path="C:\\Users\\Duchess\\Documents\\SmartSUM\\Dataset\\tempSens.rdf";
          String jsonFile=readFile(path, Charset.defaultCharset());
          //org.json.JSONObject jsonObject = new org.json.JSONObject(jsonFile);
//          //JSONArray jsonArray = jsonObject.getJSONArray("Temperature");
//          System.out.println(jsonObject.length());
//          
//          for (int i = 0, size = jsonObject.length(); i < size; i++)
//          {
//              Iterator key = jsonObject.keys();
//                while (key.hasNext()) {
//                    String k = key.next().toString();
//                    System.out.println("Timestamp : " + k + ", temperature : "
//                        + jsonObject.getString(k));
//                }
//            // System.out.println(objects.toString());
//            System.out.println("-----------");
//          }
          
          //makeJson(jsonFile);
          //makeRDF();
          //setProperty();
          //Model model=null;
          //makeRDF(model, jsonObject);
          DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
          Date date = new Date();
          System.out.println(dateFormat.format(date));
          String file="C:\\Users\\Duchess\\Documents\\SmartSUM\\Dataset\\newTest_now5.rdf";
         // String path="C:\\Users\\Duchess\\Documents\\SmartSUM\\Dataset\\temp.json";
          //readRDF(file);
         // String jsonFile=readFile(path, Charset.defaultCharset());
         // JSONObject jsonObject = new JSONObject(jsonFile);
         //loadDataIntoRDF(file);
         //sparqlTest();
         
         String erronousJSONPath="C:\\Users\\Duchess\\Documents\\SmartSUM\\Dataset\\temp_test.json";
         String cleanJSONPath="C:\\Users\\Duchess\\Documents\\SmartSUM\\Dataset\\Cleandata\\temp_test.json";
         cleanUpData(erronousJSONPath, cleanJSONPath);
        } catch (Exception e) {//OWLOntologyCreation
            e.printStackTrace();
        }
    }
    
  public static String readFile(String path, Charset encoding) throws IOException {
       byte[] encoded = Files.readAllBytes(Paths.get(path));
       return new String(encoded, encoding);
    }
  
  public static void makeJson(String file) throws Exception {
        String jsonFile=file;
        JSONParser jParser=new JSONParser();
        org.json.simple.JSONObject newJSONObject=new org.json.simple.JSONObject();
        
        Object jsonOriginalObject=jParser.parse(jsonFile);
        org.json.simple.JSONArray jsonArray=(org.json.simple.JSONArray)jsonOriginalObject;
        
        int objectCount = jsonArray.size();       
        System.out.println("Lenght: "+objectCount);
        
        int i=0;
        for(int oCount=0;oCount < objectCount;oCount++){
           
            org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) jsonArray.get(oCount);
            org.json.simple.JSONObject jsonoObjectNew=new org.json.simple.JSONObject();
            
            org.json.simple.JSONObject innerObject=null;//=new JSONObject();
            innerObject=new org.json.simple.JSONObject();       
            String hasTimeStamp = null;
            String hasValue=null;
          
            Iterator key = jsonObject.keySet().iterator();
            int keyCount=jsonObject.size();
            
            while (key.hasNext()) { 
                i++;
                innerObject=new org.json.simple.JSONObject();
                hasTimeStamp = key.next().toString();//get timestamp
                hasValue=jsonObject.get(hasTimeStamp).toString();//get value
                   
                innerObject.put("hasTimestamp",hasTimeStamp);
                innerObject.put("hasValue",hasValue);
                       
                    
                newJSONObject.put("Temp"+i,innerObject );
            }
        }

        System.out.println(newJSONObject);
        
        String path="C:\\Users\\Duchess\\Documents\\SmartSUM\\Dataset\\temp_test5.json";
        FileWriter fileWriter = new FileWriter(path);
	fileWriter.write(newJSONObject.toString());
	fileWriter.flush();
        System.out.println("Data written successfully into json. Open at: "+path);
  }
  
  public static void printJSon(org.json.JSONObject jsonObj) throws Exception{
      for (int i = 0, size = jsonObj.length(); i < size; i++)
          {
              Iterator key = jsonObj.keys();
                while (key.hasNext()) {
                    String k = key.next().toString();
                    System.out.println( k +":"+ jsonObj.getString(k));
                }
            // System.out.println(objects.toString());
            System.out.println("-----------");
          }
    }
  
  public static void makeRDF() throws Exception{
      // some definitions
    String personURI    = "http://somewhere/JohnSmith";
    String givenName    = "John";
    String familyName   = "Smith";
    String fullName     = givenName + " " + familyName;

    // create an empty Model
    Model model = ModelFactory.createDefaultModel();
    //Subject of the mail
     Property SUBJECT = model.createProperty("SUB:" );
    

    // create the resource
    //   and add the properties cascading style
    Resource johnSmith
      = model.createResource(personURI)
             .addProperty(VCARD.FN, fullName)
             .addProperty(VCARD.N,
                          model.createResource()
                               .addProperty(VCARD.Given, givenName)
                               .addProperty(VCARD.Family, familyName))
            .addProperty(VCARD.EMAIL,"PHYSICS") ;
    
    // list the statements in the Model
    StmtIterator iter = model.listStatements();
    // print out the predicate, subject and object of each statement
    while (iter.hasNext()) {
        Statement stmt      = iter.nextStatement();  // get next statement
        Resource  subject   = stmt.getSubject();     // get the subject
        Property  predicate = stmt.getPredicate();   // get the predicate
        RDFNode   object    = stmt.getObject();      // get the object

        System.out.print(subject.toString());
        System.out.print(" " + predicate.toString() + " ");
        if (object instanceof Resource) {
           System.out.print(object.toString());
        } else {
            // object is a literal
            System.out.print(" \"" + object.toString() + "\"");
        }
    }
     System.out.println(" .\nRDF format:\n");
    // now write the model in XML form to a file
    model.write(System.out);
    String path="C:\\Users\\Duchess\\Documents\\SmartSUM\\Dataset\\tmpt.rdf";
    
    //RDFWriter writer = model.getWriter();
    //m = null; // m is no longer needed.
    //writer.setErrorHandler(myErrorHandler);
//    writer.setProperty("showXmlDeclaration","true");
//    writer.setProperty("tab","8");
//    writer.setProperty("relativeURIs","same-document,relative");
//    
    OutputStream output = new FileOutputStream(path);
    //OutputStream out = new FileOutputStream("foo" + i + ".rdf");
    //writer.write(model, output,"http://example.org/" );
//                       out,
//      "http://example.org/");
//    out.close();
    
    
    RDFDataMgr.write(output, model, RDFFormat.RDFXML_ABBREV);
    
    String rdf="";
    rdf= model.toString();
   // System.out.println(rdf);
   
   
      System.out.println("Creating properties");
   
   // create the resource
       Resource r = model.createResource();                                     

      // add the property
      r.addProperty(RDFS.label, model.createLiteral("chat", "en"))
       .addProperty(RDFS.label, model.createLiteral("chat", "fr"))
       .addProperty(RDFS.label, model.createLiteral("<em>chat</em>", true));
      
      // write out the graph
      model.write(new PrintWriter(System.out));
System.out.println();
    
  }
  
  public static void setProperty()
  {
      Model model = ModelFactory.createDefaultModel();
        
        Resource subject = r("s");
        
        //model.addLiteral (subject, p("p1"), 10);
        //model.addLiteral (subject, p("p2"), 0.5);
//        model.addLiteral (subject, p("p3"), (float)0.5);
//        model.addLiteral (subject, p("p4"), l(20));
//        model.addLiteral (subject, p("p5"), l(0.99));
//        model.addLiteral (subject, p("p6"), true);
//        model.add (subject, p("p7"), l("2012-03-11", XSDDatatype.XSDdate));
//        model.add (subject, p("p8"), l("P2Y", XSDDatatype.XSDduration));

        model.setNsPrefix("example", BASE);
        //model.setNsPrefix("xsd", "http://www.w3.org/2001/XMLSchema#");
        
        
        subject = r("deji");
        
        model.addLiteral (subject, p("p1"), 23);
        model.addLiteral (subject, p("p2"), 0.9);
        model.addLiteral (subject, p("p3"), (float)0.5);
//        model.addLiteral (subject, p("p4"), l(20));
//        model.addLiteral (subject, p("p5"), l(0.99));
//        model.addLiteral (subject, p("p6"), true);
        model.add (subject, p("p7"), l("2012-03-11", XSDDatatype.XSDdate));
        model.add (subject, p("p8"), l("P2Y", XSDDatatype.XSDduration));

        
        model.setNsPrefix("arr", BASE);
        //model.setNsPrefix("xsd", "http://www.w3.org/2001/XMLSchema#");

        model.write(System.out, "RDF/XML");
  }
  
  private static Resource r ( String localname ) {
        return ResourceFactory.createResource ( BASE + localname );
    }
    
    private static Property p ( String localname ){
        return ResourceFactory.createProperty ( BASE, localname );
    }

    private static Literal l ( Object value ) {
        return ResourceFactory.createTypedLiteral ( value );
    }

    private static Literal l ( String lexicalform, RDFDatatype datatype ) {
        return ResourceFactory.createTypedLiteral ( lexicalform, datatype );
    }
      
    private static void makeRDF(Model model,org.json.JSONObject jsonObject) throws Exception{
        model = ModelFactory.createDefaultModel();
        String base="http://smartHO.org/";
        
        Iterator key = jsonObject.keys();
        while (key.hasNext()) {
            String k = key.next().toString();
            
           Resource subject = r(k);
            
            //System.out.println(k);// +":"+ jsonObject.getString(k));
            org.json.JSONObject jObj= new org.json.JSONObject(jsonObject.getString(k));
           
            Iterator keyI=jObj.keys();
            int i=1;
            while(keyI.hasNext()){
                String ki=keyI.next().toString();
                if(i==1)
                     model.add (subject, p(ki), l(jObj.getString(ki), XSDDatatype.XSDdateTimeStamp));
                else
                     model.add (subject, p(ki), l(jObj.getString(ki), XSDDatatype.XSDfloat));
                i++;
                //model.addLiteral (subject, p(ki), jObj.getString(ki));
                 //model.addLiteral (subject, p("p1"), 23);
               //System.out.println( ki +":"+ jObj.getString(ki)); 
            }
        }
        model.setNsPrefix("temperatureSensor", BASE);
        model.setNsPrefix("xsd", "http://www.w3.org/2001/XMLSchema#");
        
        model.write(System.out, "RDF/XML");
        
        String path="C:\\Users\\Duchess\\Documents\\SmartSUM\\Dataset\\tempSens.rdf";
         OutputStream output = new FileOutputStream(path);
         RDFDataMgr.write(output, model, RDFFormat.RDFXML_ABBREV);
    }
    
    private static void readRDF(String File) throws Exception{
//        String inputFile="amit.xml";
       
        Model model = ModelFactory.createDefaultModel();
        try{
                InputStream in =new  FileInputStream(File);
                if (in == null) {  
                    System.out.println("File not found");
                }  
                model.read(in,null,"RDF/XML");
                
               // Create some properties in advance for convenience.
                Property temp_time = model.createProperty( "http://SmartHO.org.com/tempSensor#timestamp");
                Property temp_value = model.createProperty( "http://SmartHO.org.com/tempSensor#value");

        // In N3, Turtle, and SPARQL, `a` is a shorthand for rdf:type.  That means
        // that each of the triples of the form 
        //
        //   <http://parking.kmi.open.ac.uk/data/parks/4934.1> a o:Parking
        //
        // is saying that the subject has rdf:type o:Parking.  That's how we'll retrieve
        // these resources.  We'll select subjects that have o_Parking as a value for 
        // rdf:type.  We'll predefine o_Parking for convenience.
         Resource tempSensor = model.createResource( "http://SmartHO.org.com/temps#" );
            //System.out.println(model.listres);
            // Now we get an iterator over the resources that have type o_Parking.
            long count=model.size();
            System.out.println(count);
           
        for (ResIterator res = model.listResourcesWithProperty( RDF.type, tempSensor ); res.hasNext(); ) {
             Resource r = res.next();

            // For each one of them, it appears that they have a mandatory lat, lon, and binAvailability, 
            // so we can retrieve those values, assuming that they'll be there.
            String time = r.getRequiredProperty( temp_time ).getObject().asLiteral().getString();
            float value = r.getRequiredProperty( temp_value ).getObject().asLiteral().getFloat();
            //boolean binAvailibility = r.getRequiredProperty( p_binaryAvailability ).getObject().asLiteral().getBoolean();

            // Some of the Parkings have an rdfs:label, but not all of them do.  For this, we'll retrieve
            // a statement, but since there might not be one, we have to check whether it's null.  If it 
            // is, then we'll make the label null, but otherwise we'll get the string value out of it.
            final Statement s = r.getProperty( RDFS.label );
            final String label = s == null ? null : s.getObject().asLiteral().getString();

            // Now you can do whatever you want with these values.  You could create an instance of another 
            // class, for instance.. I'll just print the values out.
            System.out.println( r + ":" +
                    "\n\time: " + time +
                    "\n\tvalue: " + value +
                    //"\n\tavailibility: " + binAvailibility +
                    "\n\tlabel: " + label );
            }    
                //model.write(System.out);
            }catch(Exception e){
                System.out.println(e.toString());
            }
  }
    
    private static long countRDFObjects (String File) throws Exception{
        Model model = ModelFactory.createDefaultModel();
        InputStream in =new FileInputStream(File);
        model.read(in,null,"RDF/XML");
        return model.size();  
    }
    
    private static void loadDataIntoRDF(String File)throws Exception{
        
         // Create an empty model 
        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM); 
   
        // Use the FileManager to find the input file 
        //InputStream in = FileManager.get().open(File)
       
//         if (in == null) 
//            throw new IllegalArgumentException("File: "+File+" not found"); 
         
         String path="C:\\Users\\Duchess\\Documents\\SmartSUM\\Dataset\\temp_test5.json";
          //readRDF(file);
         String jsonFile=readFile(path, Charset.defaultCharset());
         org.json.JSONObject jsonData = new org.json.JSONObject(jsonFile);
         
         int objectCount=jsonData.length();
         System.out.println("Objects: "+objectCount);
        
  
//        // Read the RDF/XML file 
//        model.read(in, null); 
//   
//        // Create a new class named "Researcher" 
//        OntClass researcher = model.createClass(ns+"Researcher"); 
//   
//        // ** TASK 6.1: Create a new class named "University" ** 
//
//        OntClass univer = model.createClass(ns + "University"); 
//
//        // ** TASK 6.2: Add "Researcher" as a subclass of "Person" ** 
//
//        model.getOntClass(ns + "Person").addSubClass(researcher); 
//
//        // ** TASK 6.3: Create a new property named "worksIn" ** 
//
//        Property worksIn = model.createProperty(ns + "worksIn"); 
//
//        // ** TASK 6.4: Create a new individual of Researcher named "Jane Smith" ** 
//
//        Individual janeSmith = researcher.createIndividual(ns + "JaneSmith"); 
//        
//        // ** TASK 6.5: Add to the individual JaneSmith the fullName, given and family names ** 
//
//        janeSmith.addProperty(VCARD.FN,"Jane Smith"); 
//        janeSmith.addProperty(VCARD.Given,"Jane"); 
//        janeSmith.addProperty(VCARD.Family,"Smith"); 
//
//        // ** TASK 6.6: Add UPM as the university where John Smith works ** 
//
//              Individual johnSmith = model.getIndividual(ns + "JohnSmith"); 
//        Individual upm = univer.createIndividual(ns + "UPM"); 
//              johnSmith.addProperty(worksIn,upm); 
//        model.write(System.out, "Turtle"); 
//        
         //model.write(System.out,"RDF/XML");
         //return;
        
        //OntClass sensorMeasurement=model.createClass(ns+"SensorMeasurement");
        OntClass physicalSensor=model.createClass(ns+"temperatureSenor");
        
        
        //Individual temperature=physicalSensor.createIndividual(ns+"PhysicalSensor");
        
        //Property producedBy=model.createProperty(ns+"producedby");
        
        
        Iterator key = jsonData.keys();
         
        while (key.hasNext()) {
            String k = key.next().toString();
            
            Individual tempData = physicalSensor.createIndividual(ns + k);
            
            
           Resource subject = r(k);
            
            //System.out.println(k);// +":"+ jsonObject.getString(k));
            org.json.JSONObject jObj= new org.json.JSONObject(jsonData.getString(k));
           
            Iterator keyI=jObj.keys();
            int i=1;
            while(keyI.hasNext()){
                String ki=keyI.next().toString();
                if(i==1)
                     //tempData.addProperty(producedBy, ki);
                     //model.add (tempData, p(ki), l(jObj.getString(ki), XSDDatatype.XSDdateTimeStamp));
                    tempData.addProperty(p(ki),l(jObj.getString(ki), XSDDatatype.XSDdateTimeStamp));
                else
                     //model.add (tempData, p(ki), l(jObj.getString(ki), XSDDatatype.XSDfloat));
                   tempData.addProperty(p(ki),l(jObj.getString(ki), XSDDatatype.XSDfloat));
                i++; 
            }
            model.setNsPrefix("PhysicalSensor", BASE);
             
            //temperature.addProperty(producedBy,sensorMeasurement); 
            //tempData.addProperty(prprt, subject);
            
        }
         model.write(System.out, "RDF/XML"); 
         OutputStream output = new FileOutputStream(File);
         RDFDataMgr.write(output, model, RDFFormat.RDFXML_ABBREV);
         System.out.println("Data written successfully into RDF. Open at: "+File);
    }
    
    private static void sparqlTest() throws Exception{
        Instant start=Instant.now();
        long beforeUsedMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        String file="C:\\Users\\Duchess\\Documents\\SmartSUM\\Dataset\\newTest_now2.rdf";
        InputStream in=new FileInputStream(file);
        Model model=ModelFactory.createDefaultModel();
        model.read(in,null,"RDF/XML");
        in.close();
        System.out.println("Size of Object: "+ model.size());
        String queryString=
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
                "PREFIX PhysicalSensor: <http://www.semanticweb.org/40011133/ontologies/2017/10/untitled-ontology-21#>"+
                "SELECT ?v ?t WHERE { "+
                "?temperatureSenor PhysicalSensor:hasTimestamp ?t ."+
                "?temperatureSenor PhysicalSensor:hasValue ?v ."+
//                "bind(xsd:dateTimeStamp(?t) as ?t)"+
//                "bind(xsd:float(?v)) as ?v)"+
                //" FILTER (?t = \"2014-05-08T22:50:00\") ."+
                " FILTER (?v = 0) ."+
                "}"+
                "ORDER BY DESC(?t)";
        Query query=QueryFactory.create(queryString);
        QueryExecution qe= QueryExecutionFactory.create(query, model);
        ResultSet rs=qe.execSelect();
        int resultCount=0;
        while(rs.hasNext()){
            resultCount++;
            QuerySolution soln=rs.nextSolution();
            Literal time=soln.getLiteral("t");
            Literal value=soln.getLiteral("v");
            
            boolean isFloat =checkIfFloat(value.getString()) ;
            if(isFloat)
                System.out.println(time.getString() +" : "+value.getString());
            else
                System.out.println(time.getString() +" : not a float value");   
        }
        qe.close();
         System.out.println("Records Affected by query: "+resultCount);
         Instant end=Instant.now();
         Duration timeElapsed=Duration.between(start, end);
         long afterUsedMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
         long actualUsedMemory=afterUsedMemory-beforeUsedMemory;
         
         String hrSize = null;

        double b = actualUsedMemory/1.0;
        double k = actualUsedMemory/1024.0;
        double m = ((actualUsedMemory/1024.0)/1024.0);
        double g = (((actualUsedMemory/1024.0)/1024.0)/1024.0);
        double t = ((((actualUsedMemory/1024.0)/1024.0)/1024.0)/1024.0);

        DecimalFormat dec = new DecimalFormat("0.00");
        
        if ( t>1 ) {
        hrSize = dec.format(t).concat(" TB");
        } else if ( g>1 ) {
            hrSize = dec.format(g).concat(" GB");
        } else if ( m>1 ) {
            hrSize = dec.format(m).concat(" MB");
        } else if ( k>1 ) {
            hrSize = dec.format(k).concat(" KB");
        } else {
            hrSize = dec.format(b).concat(" Bytes");
        }
     
         System.out.println("Time complexity: "+(timeElapsed.toMillis()/1000)+"."+(timeElapsed.toMillis()%1000)+" seconds");
         System.out.println("Space complexity: "+hrSize);
    }
    
    private static boolean checkIfFloat(String value){
        boolean isFloat=false;
        for(int i=0;i<value.length();i++){
            if(value.charAt(i)=='.'){
                isFloat=true;
                break;
            }
        }
        return isFloat;
    }
    
    private static void cleanUpData(String inputFile, String outputFile) throws Exception{
        Instant start=Instant.now();
        long beforeUsedMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        
        String jsonFile=readFile(inputFile, Charset.defaultCharset());
        org.json.JSONObject jsonData = new org.json.JSONObject(jsonFile);
         
        org.json.JSONObject newJSONObject=new org.json.JSONObject();
         
        int objectCount=jsonData.length();
        System.out.println("Total Node: "+objectCount);
         
        org.json.JSONObject innerObject=null;//new JSONObject();
        String hasTimeStamp = null;
        String hasValue=null;
          
        Iterator key = jsonData.keys();
        int i=0;
        int cleanedNode=0;
        while (key.hasNext()) {
            String k = key.next().toString();
            org.json.JSONObject jObj= new org.json.JSONObject(jsonData.getString(k));

            boolean isFloat=checkIfFloat(jObj.get("hasValue").toString());
            boolean isNotDigitZero=!("0".equals(jObj.get("hasValue").toString()));
            if(isFloat||isNotDigitZero){
                innerObject=new org.json.JSONObject(); 
                hasTimeStamp = jObj.get("hasTimestamp").toString();//get timestamp
                hasValue=jObj.get("hasValue").toString();//get value
                innerObject.put("hasTimestamp",hasTimeStamp);
                innerObject.put("hasValue",hasValue);
                i++;
                newJSONObject.put("Temp"+i,innerObject );
            }else{
                cleanedNode++;
            } 
        } 
        
        Instant end=Instant.now();
         Duration timeElapsed=Duration.between(start, end);
         long afterUsedMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
         long actualUsedMemory=afterUsedMemory-beforeUsedMemory;
         
         String hrSize = null;

        double b = actualUsedMemory/1.0;
        double k = actualUsedMemory/1024.0;
        double m = ((actualUsedMemory/1024.0)/1024.0);
        double g = (((actualUsedMemory/1024.0)/1024.0)/1024.0);
        double t = ((((actualUsedMemory/1024.0)/1024.0)/1024.0)/1024.0);

        DecimalFormat dec = new DecimalFormat("0.00");
        
        if ( t>1 ) {
        hrSize = dec.format(t).concat(" TB");
        } else if ( g>1 ) {
            hrSize = dec.format(g).concat(" GB");
        } else if ( m>1 ) {
            hrSize = dec.format(m).concat(" MB");
        } else if ( k>1 ) {
            hrSize = dec.format(k).concat(" KB");
        } else {
            hrSize = dec.format(b).concat(" Bytes");
        }
     
        //System.out.println(newJSONObject);
        System.out.println("Nodes cleaned: "+cleanedNode);
        System.out.println("valid nodes: "+newJSONObject.length());
        System.out.println("Time complexity: "+(timeElapsed.toMillis()/1000)+"."+(timeElapsed.toMillis()%1000)+" seconds");
         System.out.println("Space complexity: "+hrSize);
        
        FileWriter fileWriter = new FileWriter(outputFile);
	fileWriter.write(newJSONObject.toString());
	fileWriter.flush();
        System.out.println("Data written successfully into json. Open at: "+outputFile);
        
    }
}
