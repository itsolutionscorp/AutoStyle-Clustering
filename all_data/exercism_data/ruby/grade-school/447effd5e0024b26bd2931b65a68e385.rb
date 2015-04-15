class School

  def initialize
    @roster = Hash.new
    @roster.default_proc = proc { |hash,k| hash[k] = [] }
  end

  def to_hash
    @roster.keys.sort.each_with_object({}) { |k,h| h[k]=@roster[k] }
  end

  def add student, grade
    @roster[grade] << student
    @roster[grade] = @roster[grade].sort
  end

  def grade g
    return @roster[g]
  end
end
