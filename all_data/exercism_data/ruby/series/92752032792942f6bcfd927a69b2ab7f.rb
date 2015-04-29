class Series
  def initialize(string_of_digits)
    @digits = string_of_digits.chars.map(&:to_i)
  end

  def slices(n)
    raise ArgumentError if n > @digits.length

    @digits.map.with_index do |_, index|
      @digits.slice(index, n)
    end.reject { |x| x.length < n }
  end
end
