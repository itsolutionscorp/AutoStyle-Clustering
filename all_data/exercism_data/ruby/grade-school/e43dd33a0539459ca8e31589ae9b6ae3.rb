class School

  def initialize
    @grades = {}
  end

  def add(name, grade)
    if @grades[grade]
      @grades[grade] << name
      @grades[grade].sort!
    else
      @grades[grade] = [name]
    end
  end

  def to_hash
    @grades.sort.to_h
  end

  def grade(number)
    if @grades.has_key?(number)
      @grades[number].sort
    else
      []
    end

  end
end
