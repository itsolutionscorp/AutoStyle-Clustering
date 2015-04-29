class Squares
  def initialize(int)
    @int = int
    @debug =false
  end

  def square_of_sums
    sum**2
  end

  def sum_of_squares
    sum(square: true)
  end

  def difference
    square_of_sums - sum_of_squares
  end

  def sum(options={})
    @int.times.inject(0) do |accum, num|
      if options[:square]
        accum += ((num+1)**2)
      else
        accum += num+1
      end
    end
  end
end
