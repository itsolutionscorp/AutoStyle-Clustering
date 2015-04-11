class School

  def initialize
    @hash = {}
  end

  def add(name, grade)
    if !@hash.has_key?(grade)
      @hash[grade] = [name]
    else
      @hash[grade] << name
      @hash[grade].sort!
    end
  end

  def to_hash
    @hash.sort.to_h
  end

  def grade(grade_number)
    if @hash.has_key?(grade_number)
      @hash[grade_number]
    else
      []
    end
  end

end
