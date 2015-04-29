class Grains

  def square n
    raise ArgumentError, 'n must be a positive integer' unless n.is_a? Integer and n > 0
    [(n-1).times.reduce(1) {|sum| sum * 2 }, 1].max
  end 

  def total
    65.times.reduce {|sum,n| sum + square(n) }
  end

end
