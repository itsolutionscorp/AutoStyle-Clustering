require 'pry-byebug'

class School
  def initialize
    @s = Hash.new { |grade, name| grade[name] = [] }
  end

  def add(name, grade)
    @s[grade] << name
  end

  def to_hash
    sort = @s.map { |grade, name| [grade, name.sort] }.sort
    Hash[sort]
  end

  def grade(g_level)
    to_inject = @s.values_at(g_level)
    to_sort = to_inject.inject {|acc, x| acc + x }
    to_sort.sort
  end
end
