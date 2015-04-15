#require 'ordered_hash'

class School
  def initialize(school_name)
  end

  def db
    students_by_grade
  end

  def add(student_name, grade)
    students_by_grade[grade] << student_name
  end

  def grade(grade)
    students_by_grade[grade]
  end

  def sort
    sorted_grades.each.with_object({}) do |grade, result|
      result[grade] = sorted_students(grade)
    end
  end

  private

  def sorted_grades
    students_by_grade.keys.sort
  end

  def sorted_students(grade)
    students_by_grade[grade].sort
  end

  def students_by_grade
    @students_by_grade ||= Hash.new { |hash, grade| hash[grade] = [] }
  end
end
