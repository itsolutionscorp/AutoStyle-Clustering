class Series
  attr_reader :integers

  def initialize(string)
    @integers = string.chars.map(&:to_i)
  end

  def slices(length)
    raise ArgumentError if length > integers.size

    integers.each_cons(length).to_a
  end
end
