class School
  attr_reader :db

  def initialize(name)
    @db = {}
  end

  def add(student, grade)
    grade(grade).push(student)
  end

  def grade(grade)
    db[grade] ||= []
  end

  def sort
    sorted_grades = db.keys.sort
    sorted_grades.each_with_object({}) do |grade, h|
      h[grade] = grade(grade).sort
    end
  end
end
