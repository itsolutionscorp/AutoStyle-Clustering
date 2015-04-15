class School

  def initialize
    @roster = Hash.new([])
  end

  def add(name, grade)
    @roster[grade] += [name]
  end

  def grade(grade)
    @roster[grade].sort
  end
  
  def to_hash
    @roster.keys.sort.each_with_object({}) do |key, hash|
      hash[key] = @roster[key].sort
    end
  end
end
