class School
  attr_reader :db

  def initialize
    @db = Hash.new([])
  end

  def add(students, grade)
    @db[grade] += [students]
  end

  def grade(grade)
    @db[grade]
  end

  def sort
    sorted_grades.each_with_object({}) do |grade, sorted_db|
      sorted_db[grade] = @db[grade].sort
    end
  end

  private

  def sorted_grades
    @db.keys.sort
  end
end
