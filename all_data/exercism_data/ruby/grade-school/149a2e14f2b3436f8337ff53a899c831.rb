class School
  attr_reader :roster

  def initialize
    @roster = {}
    @roster.default_proc = proc { |hash, key| hash[key] = [] }
  end

  def add name, grade
    @roster[grade] << name
    @roster[grade].sort!
  end

  def to_hash
    keys = roster.keys.sort
    keys.each_with_object({}) { |k, return_hash| return_hash[k] = roster[k] }
  end

  def grade n
    @roster[n]
  end
end
