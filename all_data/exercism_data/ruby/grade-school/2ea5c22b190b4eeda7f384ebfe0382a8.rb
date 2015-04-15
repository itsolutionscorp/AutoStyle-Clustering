require 'pry-byebug'
class School
  def initialize
    @school = Hash.new { |grade, name| grade[name] = [] }
  end

  def to_hash
    sort = @school.map { |grade, name| [grade, name.sort] }.sort
    sort.to_h
  end

  def add(name, grade)
    @school[grade] << name
  end

  def grade(grade)
    @school.values_at(grade).pop.sort
  end
end
