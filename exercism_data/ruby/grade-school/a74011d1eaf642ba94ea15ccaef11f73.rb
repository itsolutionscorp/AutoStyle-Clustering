class School
  attr_reader :school
	
	def initialize
		@school = Hash.new
  end

  def to_hash
  	school
  end

  def add(name, grade)
  	if school[grade].nil?
  	  add_new_grade_and_student(name, grade) 
  	  sort_grades
  	else
  	  add_new_student_to_grade(name, grade)  
  	  sort_names_in_grade(grade)
  	end
  end

  def grade(grade)
  	students_in_grade_if_exists_or_empty_array(grade)
  end
 
  private

  def add_new_grade_and_student(name, grade)
  	school[grade] = [name]
  end

  def sort_grades
  	@school = Hash[school.sort]
  end

  def add_new_student_to_grade(name, grade)
		school[grade] += [name]
	end

  def sort_names_in_grade(grade)
		school[grade].sort!
	end

	def students_in_grade_if_exists_or_empty_array(grade)
		school[grade].nil? ? [] : school[grade]
	end
	
end
