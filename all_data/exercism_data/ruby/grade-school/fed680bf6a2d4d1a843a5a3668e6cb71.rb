class School
  attr_accessor :roster

  def initialize
    self.roster = Hash.new { |h,k| h[k] = [] }
  end

  def add name, grade
    klass = roster[grade]

    idx = klass.index { |n| n > name } || -1

    klass.insert idx, name
  end

  def grade n
    roster[n]
  end

  def to_hash
    Hash[roster.sort]
  end
end
