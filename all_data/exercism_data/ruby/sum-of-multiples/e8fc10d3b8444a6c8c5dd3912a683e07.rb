class SumOfMultiples
  def initialize(*factors)
    @factor_array = factors
  end
  
  def self.to(num)
    multiples = Array.new()
    [3,5].each do |n|
      multiples = multiples + 0.step(num-1,n).to_a
    end
      
    multiples.uniq.reduce(:+)
  end
  
  def to(num)
    multiples = Array.new()
    @factor_array.each do |n|
      multiples = multiples + 0.step(num-1,n).to_a
    end
    multiples.uniq.reduce(:+)
  end
end
