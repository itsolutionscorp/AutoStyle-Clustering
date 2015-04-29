class School
  attr_reader :db

  def initialize
    @db = Hash.new { |students, grade| students[grade] = Array.new }
  end

  def add(student, grade)
    db[grade] << student
  end

  def grade(grade)
    db[grade]
  end

  def sort
    Hash[tuples]
  end

  private
  def tuples
    db.map { |_, students| [_, students.sort] }.sort
  end
end
