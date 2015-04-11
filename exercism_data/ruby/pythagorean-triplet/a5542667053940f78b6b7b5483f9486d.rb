class Triplet
  def self.where max_factor: 10, min_factor: 1, sum: nil
    (min_factor..max_factor).to_a.combination(3)
      .map{|vals| Triplet.new *vals }
      .select do |triplet| 
        triplet.pythagorean? && 
        (sum.nil? || triplet.sum==sum )
      end
  end
  
  def initialize a, b, c
    @vals = [a,b,c]
  end
  def sum
    @vals.reduce(:+)
  end
  def product
    @vals.reduce(:*)
  end
  def pythagorean?
    @vals[0]**2+@vals[1]**2 == @vals[2]**2
  end
end
