class Series
  def initialize string
    @string = string
    @digits = @string.length
  end

  def slices num
    raise ArgumentError, "too many slices!" if num > @digits

    (0..(@digits - num)).map { |i| @string[i, num].split('').map(&:to_i) }
  end
end
