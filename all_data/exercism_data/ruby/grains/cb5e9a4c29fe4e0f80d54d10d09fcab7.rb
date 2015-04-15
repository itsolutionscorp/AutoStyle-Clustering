# grains.rb

class Grains

  def square n
    return 1 if n == 1
    2 * square(n-1)
  end

  def total n=64
    [*(1..n)].map {|i| square(i) }.inject &:+
  end

end
