class SumOfMultiples
  def self.to limit, factors: [3,5]
    SumOfMultiples.new(*factors).to limit
  end

  attr_accessor :factors
  def initialize *factors
    @factors = factors
  end
  
  def to limit
    multiples_to(limit).reduce(0,:+)
  end
  
  
  private
  def multiples_to limit
    return [] if factors.empty?
    values_upto(limit).select{|val| multiple_of_factor? val }
  end
  
  def values_upto limit
    (0...limit).to_a
  end
  
  def multiple_of_factor? val
    !factors.find{|factor| val%factor == 0 }.nil?
  end
end
