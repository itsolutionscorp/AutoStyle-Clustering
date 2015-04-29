class School
  attr_reader :students

  def initialize
    @students = Hash.new{ |hash, key| hash[key] = [] }
  end

  def to_hash
    Hash[students.sort_by{ |k,v| k }]
  end

  def add(student_name, grade)
    students[grade] << student_name
    students[grade].sort!
  end

  def grade(class_number)
    students[class_number]
  end
end
