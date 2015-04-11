class Series
  attr_reader :digits

  def initialize(string)
    @digits = string.chars.map(&:to_i)
  end

  def slices(slice_length)
    if slice_length > @digits.length
      raise ArgumentError, "Slice length (#{slice_length}) can't exceed number of digits (#{@digits.length})."
    end

    digits.each_cons(slice_length).to_a
  end
end
