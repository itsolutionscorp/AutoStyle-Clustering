class Grains
  def square rice
    2**(rice-1)
  end
  def total
    1.upto(64).collect {|n| square(n) }.reduce(&:+)
  end
end
