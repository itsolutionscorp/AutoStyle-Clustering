class School
  def initialize(name)
    @name = name
    @students = []
  end

  def add(student_name, grade)
    @students.push(Student.new(student_name, grade))
  end

  def db
    students_by_grade.each_with_object({}) do |(grade, students), school|
      school[grade] = students.map(&:name)
    end
  end

  def sort
    grades.sort.each_with_object({}) do |target_grade, school|
      school[target_grade] = grade(target_grade).sort
    end
  end

  def grade(target_grade)
    students = students_by_grade.fetch(target_grade, [])
    students.map(&:name)
  end

  private

  def grades
    @students.map(&:grade).uniq
  end

  def students_by_grade
    @students.group_by(&:grade)
  end
end

class Student
  attr_reader :name, :grade

  def initialize(name, grade)
    @name  = name
    @grade = grade
  end
end
