class Series
  def initialize(s)
    @s = s
  end

  def digits
    @s.chars.map(&:to_i)
  end

  # Makes an array of length n arrays from consecutive digits
  # e.g. Series.new("1234").slices(2) => [[1,2], [2,3], [3,4]]
  def slices(n)
    throw ArgumentError if n > digits.length
    Array.new(digits.length - (n-1)).map.with_index do |_, i|
      Array.new(n).map.with_index { |_, j| digits[i+j] }
    end
  end
end
