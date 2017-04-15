/**
 * StudentApp.java
 * Tanner Driggers / Thursday/6pm / Dennis Lang
 * 
 * *************EXTRA CREDIT*************
 * This class is used as the Student Object
 */

public class Student {
	private String name;
	private String WID;
	private double labs, projects, codelabs, classExams, finalExam;
	
	public Student() {
		setName("no name");
		setWID("no WID");
		setLabs(0);
		setProjects(0);
		setCodelabs(0);
		setClassExams(0);
		setFinalExam(0);
	}
	
	public Student(String name, String wid, double labs, double projects, double codelabs, double classExams, double finalExam) {
		this.setName(name);
		this.setWID(wid);
		this.setLabs(labs);
		this.setProjects(projects);
		this.setCodelabs(codelabs);
		this.setClassExams(classExams);
		this.setFinalExam(finalExam);
	}
	
	/**
	 * this method is used to get the overall percent for this instance of student
	 * @return
	 */
	public Double OverallPercent() {
		int totalPointsPossible = StudentApp.totalLabPoints + StudentApp.totalProjectPoints + StudentApp.totalCodelabPoints + 
				StudentApp.totalExamPoints + StudentApp.totalFinalExamPoints;
		double adjustedLabPoints = totalPointsPossible * 0.1;
		double adjustedProjectPoints = totalPointsPossible * 0.15;
		double adjustedCodelabPoints = totalPointsPossible * 0.1;
		double adjustedExamsPoints = totalPointsPossible * 0.45;
		double adjustedFinalExamPoints = totalPointsPossible * 0.2;
		double lab = (this.getLabs() / StudentApp.totalLabPoints) * adjustedLabPoints;
		double proj = (this.getProjects() / StudentApp.totalProjectPoints) * adjustedProjectPoints;
		double codelab = (this.getCodelabs() / StudentApp.totalCodelabPoints) * adjustedCodelabPoints;
		double exams = (this.getClassExams() / StudentApp.totalExamPoints) * adjustedExamsPoints;
		double finalExams = (this.getFinalExam() / StudentApp.totalFinalExamPoints) * adjustedFinalExamPoints;
		double gradePercentage = (lab + proj + codelab+ exams + finalExams) / totalPointsPossible;
		return gradePercentage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWID() {
		return WID;
	}

	public void setWID(String WID) {
		this.WID = WID;
	}

	public double getLabs() {
		return labs;
	}

	public void setLabs(double labs2) {
		this.labs = labs2;
	}

	public double getProjects() {
		return projects;
	}

	public void setProjects(double projects2) {
		this.projects = projects2;
	}

	public double getCodelabs() {
		return codelabs;
	}

	public void setCodelabs(double codelabs2) {
		this.codelabs = codelabs2;
	}

	public double getClassExams() {
		return classExams;
	}

	public void setClassExams(double classExams2) {
		this.classExams = classExams2;
	}

	public double getFinalExam() {
		return finalExam;
	}

	public void setFinalExam(double finalExam2) {
		this.finalExam = finalExam2;
	}
	
}
