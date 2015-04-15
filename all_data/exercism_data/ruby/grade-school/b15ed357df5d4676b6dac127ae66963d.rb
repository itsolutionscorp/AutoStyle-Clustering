class School 
  def initialize
    @grades = {}
  end

  def to_hash
    Hash[@grades.sort]
  end

  def grade(number)
    return @grades[number.to_i] || []
  end

  def add(student, grade)
    if @grades[grade]
      @grades[grade] << student
      @grades[grade].sort!
    else
      @grades[grade] = [student]
    end
  end

end
