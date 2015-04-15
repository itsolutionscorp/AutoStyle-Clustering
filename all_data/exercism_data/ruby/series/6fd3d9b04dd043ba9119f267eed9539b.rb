class Series
  def initialize string
    @digits = string.split("").map(&:to_i)
  end

  def slices number
    raise ArgumentError if number > @digits.size
    [*0..@digits.size - number].each_with_object([]) do |i, result|
      result << @digits[i, number]
    end
  end
end
