class School
  attr_reader :school
	
  def initialize
    @school = Hash.new
  end

  def to_hash
  	school
  end

  def add(name, grade)
    if grade_exists?(grade)
      add_student_sorted_to_grade(name,grade)
    else
      add_student_sort_grades(name, grade)
    end
  end

  def grade(grade)
  	students_in_grade_if_exists_or_empty_array(grade)
  end
 
  private

  def grade_exists?(grade)
   !school[grade].nil?
  end

  def add_student_sorted_to_grade(name, grade)
    (school[grade] << name).sort!
  end

  def add_student_sort_grades(name, grade)
    school[grade] = [name]
    @school = Hash[school.sort]
  end

  def students_in_grade_if_exists_or_empty_array(grade)
    school.fetch(grade, []) 
  end
	
end
