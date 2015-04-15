class Grains
  def square(num)
    2 ** (num - 1)
  end

  def total
    sum = 0
    1.upto(64) {|i| sum = sum + square(i)}
    sum

  end
end
