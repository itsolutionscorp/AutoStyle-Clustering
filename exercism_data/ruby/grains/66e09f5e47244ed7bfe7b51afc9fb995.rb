class Grains
  def initialize
  end
  def square(n)
    return n if n==1 || n==2
    (2**n)/2
  end

  def total
    sum=0
    1.upto(64) {|item| sum+=square(item)}
    sum
  end
end
