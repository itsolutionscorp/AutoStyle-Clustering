class School

  def initialize
    @roster ||= Hash.new { |hash,k| hash[k] = [] }
  end

  def to_hash
    @roster.keys.sort.each_with_object({}) { |k,h| h[k]=@roster[k] }
  end

  def add student, grade
    @roster[grade] << student
    @roster[grade].sort!
  end

  def grade g
    @roster[g]
  end

end
