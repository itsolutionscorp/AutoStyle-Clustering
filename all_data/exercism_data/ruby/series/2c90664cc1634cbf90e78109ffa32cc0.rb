class Series

  attr_reader :sequence

  def initialize sequence
    @sequence = sequence
  end

  def slices length
    raise ArgumentError if sequence.length < length

    digits.each_cons( length ).to_a
  end

private

  def digits
    @digits ||= sequence.chars.map &:to_i
  end

end
