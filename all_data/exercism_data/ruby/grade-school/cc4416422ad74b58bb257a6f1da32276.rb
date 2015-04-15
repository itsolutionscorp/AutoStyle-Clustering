class School
  def initialize
    @student_grade_hash = Hash.new []
  end

  def to_hash
    @student_grade_hash.sort.to_h.each{ |key,value| value.sort! }
  end

  def grade grade_num
    to_hash[grade_num] || []
  end

  def add name, grade
    @student_grade_hash[grade] += [name]
  end
end
