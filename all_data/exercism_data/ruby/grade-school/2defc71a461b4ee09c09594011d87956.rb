class School
  def initialize
    @roster = {}
    @roster.default_proc = proc { |hash, key| hash[key] = [] }
  end

  def add(name, grade)
    @roster[grade] << name
    @roster[grade].sort!
  end

  def to_hash
    keys = roster.keys.sort
    return_hash = {}
    keys.each do |k|
      return_hash[k] = roster[k]
    end
    return_hash
  end

  def grade(n)
    @roster[n]
  end
end
