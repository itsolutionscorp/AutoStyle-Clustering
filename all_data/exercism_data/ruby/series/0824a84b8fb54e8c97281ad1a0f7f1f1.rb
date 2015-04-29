class Series
  def initialize string
    @digits = string.split("").map(&:to_i)
    @result = Array.new
  end

  def slices number
    if @digits.count < number
      raise ArgumentError
    else
      while @digits.count >= number
        @result << @digits.take(number)
        @digits.shift
      end
    end
    @result
  end
end
