class Grains
  def square(number)
    1.upto(number).inject { |n| n * 2 }
  end

  def total
    1.upto(64).inject { |sum, n| sum + square(n) }
  end
end
