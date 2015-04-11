class School
  def initialize
    @school = Hash.new{ [] }
  end

  def to_hash
    @school.sort.reduce({}) { |res, (k, v)| res[k] = v.sort; res }
  end

  def add(name, grade)
    @school[grade] <<= name
  end

  def grade(grade)
    result = @school[grade] || []
    result.sort
  end
end
