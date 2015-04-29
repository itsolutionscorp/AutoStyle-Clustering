class School
  attr_accessor :db

  def initialize
    @db = Hash.new { |db, level| db[level] = [] }
  end

  def add(student_name, grade_level)
    @db[grade_level] << student_name
  end

  def grade(level)
    @db[level]
  end

  def sort
    @db.keys.sort.each_with_object({}) do |grade_level, db|
      db[grade_level] = @db[grade_level].sort
    end
  end
end
