class School
  def initialize
    @roster = {}
  end

  def add(name, grade)
    (@roster.keys.include?(grade)) ? @roster[grade] << name : @roster[grade] = [name]
  end

  def to_hash
    sort = {}
    keys = @roster.keys.sort
    keys.each {|key| sort[key] = (@roster[key]).sort}
    return sort
  end

  def grade(grade)
    return [] if @roster[grade].nil?
    return @roster[grade].sort
  end
end
