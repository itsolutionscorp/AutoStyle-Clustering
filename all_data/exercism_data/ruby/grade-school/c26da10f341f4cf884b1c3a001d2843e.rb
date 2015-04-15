class School
  attr_reader :archives
  def initialize
    @archives = Hash.new([])
  end

  def to_hash
    Hash[@archives.sort]
  end

  def add name, grade
    (@archives[grade] += [name]).sort!
  end

  def grade num
    @archives[num]
  end
end
