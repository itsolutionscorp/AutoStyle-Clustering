class School

  def initialize
    @grade_hash = {}
  end

  def to_hash
    @grade_hash
  end

  def add(name, grade)
    if @grade_hash[grade]
      @grade_hash[grade] << name
    else
      @grade_hash[grade] = [name]
    end
    @grade_hash
  end

  def grade(grade)
    if @grade_hash[grade]
      @grade_hash[grade].sort
    else
      []
    end
  end

end
