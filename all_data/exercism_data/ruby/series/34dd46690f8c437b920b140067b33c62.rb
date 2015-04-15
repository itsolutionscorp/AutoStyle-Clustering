class Series

  def initialize(string)
    @digits = string.split('').map { |x| x.to_i }
  end

  def slices(n)
    raise ArgumentError if n > @digits.length
    double = @digits * 2
    @digits[0..-n].map.with_index { |e, i| double[i...i+n] }
  end
end
