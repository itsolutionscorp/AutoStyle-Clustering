class School
  attr_reader :db

  def initialize
    @db = Hash.new(Array.new)
  end

  def add(student, grade)
    if db[grade].any?
      db[grade] << student
    else
      db[grade] = [student]
    end
  end

  def grade(grade_number)
    db[grade_number]
  end

  def sort
    sorted_by_grade = Hash[db.sort]

    sorted_by_grade.each do |grade, students|
      sorted_by_grade[grade] = students.sort
    end

    sorted_by_grade
  end
end
