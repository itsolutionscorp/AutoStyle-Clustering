class School

  def initialize
    @register = Hash.new {|hash, key| hash[key] = [] }
  end

  def add(grade, name)
    register[name] << grade
  end

  def grade(year)
    register[year].sort
  end

  def to_hash
    sort_by_name
    sort_by_grade.to_h
  end

  private

  attr_reader :register

  def sort_by_name
    register.keys.each do |grade|
      register[grade].sort!
    end
  end

  def sort_by_grade
    register.sort_by {|grade, name| grade}
  end
end
