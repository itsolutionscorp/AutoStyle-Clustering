class School
  def initialize
    @grades = Hash.new { |hash, key| hash[key] = Grade.new }
  end

  def add(student, grade)
    grades[grade].add(student)
  end

  def grade(grade_number)
    grades[grade_number].db
  end

  def db
    grades.inject({}) { |acc, (grade_number, grade)| 
      acc.merge(grade_number => grade.db)
    }
  end

  def sort
    grades.sort.inject({}) { |acc, (grade_number, grade)|
      acc.merge(grade_number => grade.sort)
    }
  end

private
  attr_reader :grades
end

class Grade
  def initialize
    @students = []
  end

  def add(student)
    students << student
  end

  def db
    students.dup
  end

  def sort
    students.sort
  end

private
  attr_reader :students
end
