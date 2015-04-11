class Series
  def initialize(number)
    @number = number
  end

  def slices(n)
    if n > @number.length
      raise ArgumentError
    else
      num_solutions = @number.length - n + 1
      (0..num_solutions - 1).map do |k|
        @number[k..k+n-1].split("").map { |j| j.to_i }
      end
    end
  end
end
