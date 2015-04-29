class School

  def initialize
    @index = Hash.new { |hash,key| hash[key] = [] }
  end

  def add student_name, student_grade
    @index[student_grade.to_i] << student_name
    @index[student_grade.to_i].sort!
  end

  def grade student_grade
    @index[student_grade].sort!
  end

  def to_hash
    @index = Hash[@index.sort]
    @index
  end
end
