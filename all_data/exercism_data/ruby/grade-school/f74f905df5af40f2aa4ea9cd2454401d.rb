class School

  def initialize
    @STUDENTS = Hash.new
  end

  def to_hash
    create_and_sort_hash
  end

  def add(name, grade)
    if grade_exists_in_hash(grade)
      student_repository[grade] << name
    else
      new_student = {grade => [name]}
      student_repository.merge!(new_student)
    end
  end

  def grade(grade)
    if grade_exists_in_hash(grade)
      student_repository[grade].sort
    else
      []
    end
  end

  private

  def create_and_sort_hash
    student_repository.each { |grade, names| names.sort!}
    student_repository.each.sort.to_h
  end

  def grade_exists_in_hash(grade)
    @STUDENTS.has_key?(grade)
  end

  def student_repository(*option)
    @STUDENTS
  end

end
