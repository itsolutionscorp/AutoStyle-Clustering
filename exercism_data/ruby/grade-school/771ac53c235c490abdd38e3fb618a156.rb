class School
  def initialize
    @school = {}
  end

  def add(student, grade)
    if @school[grade].nil?
      @school[grade] = [student]
    else
      @school[grade] << student
      @school[grade].sort!
    end
  end

  def grade(num)
    @school[num].nil? ? [] : @school[num]
  end

  def to_hash
    sorted = @school.to_a.sort
    Hash[sorted]
  end
end
