# encoding: utf-8

# School implmentation for exercism exercise
class School
  def initialize
    @db = {}
  end

  def db
    # deep clone for no outside modification
    grades.each_with_object({}) do |grade_num, h|
      h[grade_num] = grade(grade_num)
    end
  end

  def add(name, grade_num)
    @db[grade_num] ||= []
    @db[grade_num] << name unless @db[grade_num].include?(name)
  end

  def grade(grade_num)
    (@db[grade_num] || []).dup
  end

  def sort
    grades.sort.each_with_object({}) do |grade_num, h|
      h[grade_num] = grade(grade_num).sort
    end
  end

  private

  def grades
    @db.keys
  end
end
