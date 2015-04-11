class Series
  def initialize(number)
    @number = number
  end

  def slices(n)
    if n > @number.length
      raise ArgumentError
    else
      (0..@number.length - n).map do |k|
        @number[k..k+n-1].chars.map(&:to_i) 
      end
    end
  end
end
