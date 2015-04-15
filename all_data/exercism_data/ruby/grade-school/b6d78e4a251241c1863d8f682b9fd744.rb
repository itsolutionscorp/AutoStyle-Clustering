class School
  attr_accessor :grades

  def initialize
    @grades = {}
  end

  def grade(grade)
    grades[grade] || []
  end

  def db
    @grades
  end

  def add(name, grade)
    grades[grade] ||= []
    grades[grade].push(name)
  end

  def sort
    Hash[@grades.map{ |k,v| [k, v.sort] }.sort]
  end
end
