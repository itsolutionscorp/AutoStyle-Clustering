class SumOfMultiples
  def initialize(*factors)
    @factor_array = factors
  end
  
  def self.to(num)
    new(3,5).to(num)
  end
  
  def to(num)
    multiples = Array.new()
    @factor_array.each do |n|
      multiples = multiples + 0.step(num-1,n).to_a
    end
    multiples.uniq.reduce(:+)
  end
end
