class School
  def initialize
    @students = Hash.new
  end

  def add(s, g)
    @students[g] = @students[g].nil? ? [s] : @students[g].push(s)
    @students[g] = @students[g].sort
  end

  def to_hash
    return @students.sort.to_h
  end

  def grade(g)
    return !@students[g].nil? ? @students[g] : []
  end
end
