package entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import entities.enums.WorkerLevel;

public class Worker {
	
	private String name;
	private WorkerLevel level;
	private Double baseSalary;
	
	private Department department; 
	//composicao do tipo "tem-uma" - nesse caso ele vai junto no construtor
	private List<HourContract> contracts = new ArrayList<>(); 
	// composicao do tipo "tem-varios"  - nesse caso a lista e iniciada vazia junto na instanciacao
	
	public Worker() {
	}

	public Worker(String name, WorkerLevel level, Double baseSalary, Department department) {
		this.name = name;
		this.level = level;
		this.baseSalary = baseSalary;
		this.department = department;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public WorkerLevel getLevel() {
		return level;
	}

	public void setLevel(WorkerLevel level) {
		this.level = level;
	}

	public Double getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(Double baseSalary) {
		this.baseSalary = baseSalary;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<HourContract> getContracts() {
		return contracts;
	}
	
	public void addContract(HourContract contract) {
		contracts.add(contract);
	}
	
	public void removeContract(HourContract contract) {
	    contracts.remove(contract);
	}
	
	public double income(int year, int month) { // mes e ano utilizados como argumento
		double sum = baseSalary;
		Calendar cal = Calendar.getInstance();  //Objeto calendar - forma de chamar/importar e instanciar ele
		for (HourContract c : contracts) { 
			cal.setTime(c.getDate()); //serve para adicionar a data dentro do calendario
			//no caso aqui a data do contrato feito pelo worker
			int c_year = cal.get(Calendar.YEAR); //pega como argumento para a variavel c_year - o ano do contrato
			int c_month = 1 + cal.get(Calendar.MONTH); //pega como argumento para a variavel c_month - o mes do contrato		
			// necessario adicionar 1 pois ele comeca em 0.
			if (year == c_year && month == c_month) {
		    //esta comparando o mes e o ano utlizados inicialmente como arguento 
			//com o mes e o ano do array list. Que estao sendo lidos pelo objeto calendar
				
				sum += c.totalValue(); // vai somar a valor das horas trabalhadas naquele ano e mes.
			}
		}
		return sum;
	}
}
