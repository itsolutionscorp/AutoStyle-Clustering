class Series
  def initialize str_of_digits
    @digits = str_of_digits.chars.map { |x| x.to_s.to_i }
  end

  def slices series
    raise ArgumentError.new('Not enough digits') if @digits.count < series
    result = []
    (0..@digits.count-series).step(1).each{|x| result << (@digits[x,series])}
    result
  end
end
