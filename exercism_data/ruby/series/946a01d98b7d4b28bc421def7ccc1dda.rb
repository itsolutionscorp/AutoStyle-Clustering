class Series
  attr_reader :sequence

  def initialize(string)
    @sequence = string.chars.map(&:to_i)
  end

  def slices(size)
    raise ArgumentError if size > sequence.size
    sequence.each_cons(size).to_a
  end
end
