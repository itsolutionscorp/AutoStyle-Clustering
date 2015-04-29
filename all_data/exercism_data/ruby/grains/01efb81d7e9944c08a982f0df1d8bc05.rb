class Grains

  MAX_SQUARES = 64

  def square(i)
    if i == 1
      1
    else
      square(i-1) * 2
    end
  end

  def total
    (1..MAX_SQUARES).map{|i| square(i)}.reduce(:+)
  end

end
