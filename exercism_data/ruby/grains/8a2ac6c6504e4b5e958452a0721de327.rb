class Grains
  def square(number)
    number == 1 ? 1 : 2 * square(number-1)
  end

  def total
    (1..64).map {|n| square n }.reduce(:+)
  end
end
