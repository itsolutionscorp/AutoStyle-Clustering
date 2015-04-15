require 'pry'
class Grains
  def square(s)
    2 ** (s - 1)
  end

  def total
    (1..64).to_a.inject(0){|s,i| s + square(i) }
  end
end
