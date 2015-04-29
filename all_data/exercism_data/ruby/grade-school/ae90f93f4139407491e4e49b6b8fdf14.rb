require 'pry'

class School
  attr_reader :db

  def initialize
    @db = Hash.new{ |h,k| h[k]=[] }
  end

  def add(name, grade_num)
    grade(grade_num) << name
  end

  def grade grade_num
    @db[grade_num]
  end

  def sort
    sorted = @db.map{ |k, v| [k, v.sort] }.sort
    Hash[sorted]
  end
end
