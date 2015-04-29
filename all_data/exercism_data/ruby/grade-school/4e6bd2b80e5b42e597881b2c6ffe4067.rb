class School
  def initialize
    @roster = Hash.new([].freeze) # Immutable Array
  end

  def add(name, gr)
    @roster[gr] += [name]
    @roster[gr].sort!
  end

  def grade(gr)
    @roster[gr]
  end

  def to_hash
    @roster.nil? ? {} : Hash[@roster.sort]
  end
end
