class Series
  def initialize(string)
    @string = string
  end

  def digits
    string.chars.map(&:to_i)
  end

  def slices(slice_length)
    if slice_length > string.length
      raise ArgumentError, "Slice length (#{slice_length}) can't exceed string length (#{string.length})."
    end

    digits.each_cons(slice_length).to_a
  end

  private

  attr_reader :string
end