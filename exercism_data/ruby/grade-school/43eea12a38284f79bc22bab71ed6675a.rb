class School
  def initialize
    @students = {}
  end

  def add(student_name, grade)
    (@students[grade] ||= []) << student_name
  end

  def grade(num)
    @students[num] ? @students[num].sort : []
  end

  def to_hash
    @students.each_value(&:sort!)
    Hash[@students.sort]
  end
end
