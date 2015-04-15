class School

  def initialize
    @register = Hash.new {|hash, key| hash[key] = [] }
  end

  def add(grade, name)
    register[name] << grade
  end

  def grade(year)
    sort_names
    register[year]
  end

  def to_hash
    sort_names
    sort_grades.to_h
  end

  private

  attr_reader :register

  def sort_names
    register.keys.each do |grade|
      register[grade].sort!
    end
  end

  def sort_grades
    register.sort_by {|grade, name| grade }
  end
end
