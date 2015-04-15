# encoding: utf-8

# School implmentation for exercism exercise
class School
  def initialize
    @db = {}
  end

  def db
    # no outside modification
    @db.dup
  end

  def add(name, grade_num)
    @db[grade_num] ||= []
    @db[grade_num] << name
  end

  def grade(grade_num)
    (@db[grade_num] || []).dup
  end

  def sort
    grades = @db.keys
    grades.sort.reduce({}) do |accum, grade_num|
      accum[grade_num] = @db[grade_num].sort
      accum
    end
  end
end
