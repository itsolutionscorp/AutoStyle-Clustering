class School
  attr_reader :students
  def initialize
    @students = {}
  end

  def to_hash
    @students.to_a.sort { |x,y| x[0] <=> y[0]}.to_h
  end

  def add(student, grade)
    if @students[grade]
      @students[grade] << student
    else
      @students[grade] = [student]
    end
    @students[grade].sort!
  end

  def grade(num)
    if @students[num]
      @students[num].sort 
    else
      []
    end
  end

end
