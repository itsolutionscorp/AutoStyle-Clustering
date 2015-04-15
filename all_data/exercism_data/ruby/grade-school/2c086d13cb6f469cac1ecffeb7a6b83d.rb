class School
  def initialize
    @hash = {}
    @hash.default = []
  end

  def add(student, grade)
    if @hash.has_key? grade
      @hash[grade] << student
      @hash[grade].sort!
    else
      @hash[grade] = [student]
      @hash = @hash.sort.to_h
    end
  end

  def grade(n)
    @hash[n]
  end

  def to_hash
    @hash
  end
end
