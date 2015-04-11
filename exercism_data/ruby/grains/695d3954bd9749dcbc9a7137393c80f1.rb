class Grains

  def square num
    return 2**(num - 1)
  end

  def total
    @sum ||= calc_total
  end

  def calc_total
    (1..64).reduce(0) do |sum, num|
      sum + square(num)
    end
  end

end
