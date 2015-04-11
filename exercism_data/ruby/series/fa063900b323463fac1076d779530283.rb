class Series
  def initialize string_of_digits
    @numbers = string_of_digits.chars.map(&:to_i)
  end

  def slices num
    raise ArgumentError if num > @numbers.length
    @numbers.each_cons(num).to_a
  end
end
