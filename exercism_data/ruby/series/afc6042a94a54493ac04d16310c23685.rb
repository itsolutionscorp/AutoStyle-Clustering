class Series
  def initialize(digit_string)
    @digit_string = digit_string
  end

  def slices(slice_size)
    raise ArgumentError if slice_size > @digit_string.size
    digits = @digit_string.split('').map(&:to_i)
    groups = []
    for i in 0..(digits.size - slice_size)
      groups << digits[i..(i+slice_size - 1)]
    end
    groups
  end
end
