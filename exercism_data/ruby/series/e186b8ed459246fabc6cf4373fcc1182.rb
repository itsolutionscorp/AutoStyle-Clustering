class Series
  attr_reader :string
  def initialize(string)
    @string = string
  end

  def slices(length)
    raise ArgumentError, "Slice length is bigger than the original string" if length > string.length
    string.chars.map(&:to_i).each_cons(length).to_a
  end
end
