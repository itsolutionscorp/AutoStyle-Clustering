class School
  TOTAL_GRADES = 7

  attr_reader :data

  def initialize
    @data = {}
    (1..TOTAL_GRADES).map {|grade| @data[grade] = []}
  end

  def to_hash
    data.reject{ |k,v| v.empty? }
  end

  def add(student_name, grade)
    data[grade] << student_name
    data[grade].sort!
  end

  def grade(class_number)
    data[class_number]
  end
end
