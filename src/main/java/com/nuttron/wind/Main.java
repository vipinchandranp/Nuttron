package com.nuttron.wind;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.nuttron.wind.dao.EntityDAO;
import com.nuttron.wind.dao.EntityDAOImpl;
import com.nuttron.wind.dao.entity.A;
import com.nuttron.wind.dao.entity.Aa;
import com.nuttron.wind.dao.entity.Aaa;
import com.nuttron.wind.dao.entity.Aab;
import com.nuttron.wind.dao.entity.Aac;
import com.nuttron.wind.dao.entity.Ab;
import com.nuttron.wind.dao.entity.Aba;
import com.nuttron.wind.dao.entity.Abb;
import com.nuttron.wind.dao.entity.Abc;
import com.nuttron.wind.dao.entity.Ac;
import com.nuttron.wind.dao.entity.Aca;
import com.nuttron.wind.dao.entity.Acb;
import com.nuttron.wind.dao.entity.Acc;
import com.nuttron.wind.dao.entity.Z;

public class Main {
	public static void main(String[] args) throws Exception {

		EntityDAO dao = new EntityDAOImpl();

		A a = new A();
		a.setAValue("A_VALUE");

		Z z = new Z();
		z.setZValue("Z_VALUE");

		a.setZ(z);

		Aa aa = new Aa();
		aa.setAaValue("AA_1");

		Aaa aaa = new Aaa();
		aaa.setAaaValue("AAA_1");

		aa.setAaas(new HashSet<Aaa>());
		aa.getAaas().add(aaa);

		Aab aab = new Aab();
		aab.setAabValue("AAB_1");

		aa.setAabs(new HashSet<Aab>());
		aa.getAabs().add(aab);

		Aac aac = new Aac();
		aac.setAacValue("AAC_1");

		aa.setAacs(new HashSet<Aac>());
		aa.getAacs().add(aac);

		a.setAas(new HashSet<Aa>());
		a.getAas().add(aa);

		Ab ab = new Ab();
		ab.setAbValue("AB_1");

		Aba aba = new Aba();
		aba.setAbaValue("ABA_1");

		ab.setAbas(new HashSet<Aba>());
		ab.getAbas().add(aba);

		Abb abb = new Abb();
		abb.setAbbValue("ABB_1");

		ab.setAbbs(new HashSet<Abb>());
		ab.getAbbs().add(abb);

		Abc abc = new Abc();
		abc.setAbcValue("ABC_1");

		ab.setAbcs(new HashSet<Abc>());
		ab.getAbcs().add(abc);

		a.setAbs(new HashSet<Ab>());
		a.getAbs().add(ab);

		Ac ac = new Ac();
		ac.setAcValue("AC_1");

		Aca aca = new Aca();
		aca.setAcaValue("ACA_1");

		ac.setAcas(new HashSet<Aca>());
		ac.getAcas().add(aca);

		Acb acb = new Acb();
		acb.setAcbValue("ACB_1");

		ac.setAcbs(new HashSet<Acb>());
		ac.getAcbs().add(acb);

		Acc acc = new Acc();
		acc.setAccValue("ACC_1");

		ac.setAccs(new HashSet<Acc>());
		ac.getAccs().add(acc);

		a.setAcs(new HashSet<Ac>());
		a.getAcs().add(ac);

		List<A> entities = dao.getEntity(a);
		for (A entity : entities) {
			System.out.println("A value >>>>>> " + entity.getAId());
			System.out.println("Z value >>>>>> " + entity.getZ().getZValue());
			Set<Aa> aasSet = entity.getAas();
			for (Aa aas : aasSet) {
				System.out.println("Aa value >>>>>> " + aas.getAaId());
				Set<Aaa> aaasSet = aas.getAaas();
				for (Aaa aaas : aaasSet) {
					System.out.println("Aaa value >>>>>> " + aaas.getAaaId());
				}
				Set<Aab> aabsSet = aas.getAabs();
				for (Aab aabs : aabsSet) {
					System.out.println("Aab value >>>>>> " + aabs.getAabId());
				}
				Set<Aac> aacsSet = aas.getAacs();
				for (Aac aaacs : aacsSet) {
					System.out.println("Aaa value >>>>>> " + aaacs.getAacId());
				}
			}
			Set<Ab> absSet = entity.getAbs();
			for (Ab abs : absSet) {
				System.out.println("Ab value >>>>>> " + abs.getAbId());
				Set<Aba> abasSet = abs.getAbas();
				for (Aba aaas : abasSet) {
					System.out.println("Aba value >>>>>> " + aaas.getAbaId());
				}
				Set<Abb> abbsSet = abs.getAbbs();
				for (Abb abbs : abbsSet) {
					System.out.println("Abb value >>>>>> " + abbs.getAbbId());
				}
				Set<Abc> abcsSet = abs.getAbcs();
				for (Abc abcs : abcsSet) {
					System.out.println("Abc value >>>>>> " + abcs.getAbcId());
				}
			}
			Set<Ac> acsSet = entity.getAcs();
			for (Ac acs : acsSet) {
				System.out.println("Ab value >>>>>> " + acs.getAcId());
				Set<Aca> acasSet = acs.getAcas();
				for (Aca acas : acasSet) {
					System.out.println("Aca value >>>>>> " + acas.getAcaId());
				}
				Set<Acb> acbsSet = acs.getAcbs();
				for (Acb acbs : acbsSet) {
					System.out.println("Acb value >>>>>> " + acbs.getAcbId());
				}
				Set<Acc> accsSet = acs.getAccs();
				for (Acc accs : accsSet) {
					System.out.println("Aca value >>>>>> " + accs.getAccId());
				}
			}
		}
	}
}