class Series
  def initialize num_string
    @digits = num_string.each_char.map(&:to_i)
  end

  def slices num
    raise ArgumentError, "too many slices!" if num > @digits.size

    @digits.each_cons(num).to_a
  end
end
