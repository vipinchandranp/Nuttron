package com.nuttron.wind;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.jsonschema2pojo.DefaultGenerationConfig;
import org.jsonschema2pojo.GenerationConfig;
import org.jsonschema2pojo.Jackson2Annotator;
import org.jsonschema2pojo.SchemaGenerator;
import org.jsonschema2pojo.SchemaMapper;
import org.jsonschema2pojo.SchemaStore;
import org.jsonschema2pojo.SourceType;
import org.jsonschema2pojo.rules.RuleFactory;

import com.sun.codemodel.JCodeModel;

public class JSONtoPOJOTest {

	public static void main(String[] args) {
		/*JCodeModel codeModel = new JCodeModel();

		File source =  new File("D:/vpnGit/Nuttron/src/main/resources/A.json");

		GenerationConfig config = new DefaultGenerationConfig() {
			@Override
			public boolean isGenerateBuilders() { // set config option by overriding method
				return true;
			}
		};

		SchemaMapper mapper = new SchemaMapper(
				new RuleFactory(config, new Jackson2Annotator(config), new SchemaStore()), new SchemaGenerator());
		try {
			mapper.generate(codeModel, "Test", "com.example", source.toURI().toURL());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			codeModel.build(new File("D:/vpnGit/Nuttron/src/main/resources/"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		String packageName="com.cooltrickshome";  
        File inputJson= new File("D:/vpnGit/Nuttron/src/main/resources/A.json");  
        File outputPojoDirectory=new File("D:/vpnGit/Nuttron/src/main/resources/");  
        try {  
             new JSONtoPOJOTest().convert2JSON(inputJson.toURI().toURL(), outputPojoDirectory, packageName, inputJson.getName().replace(".json", ""));  
        } catch (IOException e) {  
             // TODO Auto-generated catch block  
             System.out.println("Encountered issue while converting to pojo: "+e.getMessage());  
             e.printStackTrace();  
        }  

	}
	
	public void convert2JSON(URL inputJson, File outputPojoDirectory, String packageName, String className) throws IOException{
		JCodeModel codeModel = new JCodeModel();

		URL source = inputJson;

		GenerationConfig config = new DefaultGenerationConfig() {
		@Override
		public boolean isGenerateBuilders() { // set config option by overriding method
		return true;
		}
		public SourceType getSourceType(){
            return SourceType.JSON;
        }
		};
		SchemaMapper mapper = new SchemaMapper(new RuleFactory(config, new Jackson2Annotator(config), new SchemaStore()), new SchemaGenerator());
		mapper.generate(codeModel, className, packageName, source);

		codeModel.build(outputPojoDirectory);
	}
}
