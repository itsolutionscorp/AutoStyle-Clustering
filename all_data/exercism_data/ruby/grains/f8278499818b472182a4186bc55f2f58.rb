class Grains
  def initialize
  end
  
  def square(n)
    return 2**(n-1)
  end
  
  def total
=begin
  #To increase the speed
    total=1
    sq = 1
    63.times do
      sq = sq*2
      total += sq
    end
    return total
=end
    
    total = 0
    (1..64).each {|e| total += square(e)}
    return total
  end
end
