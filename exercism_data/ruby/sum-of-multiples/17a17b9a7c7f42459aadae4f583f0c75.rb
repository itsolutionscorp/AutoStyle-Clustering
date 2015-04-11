class SumOfMultiples
  def self.to limit
    SumOfMultiples.new(3,5).to limit
  end

  attr_reader :factors
  def initialize *factors
    @factors = factors
  end

  def to limit
    multiples_to(limit).reduce(0,:+)
  end

  private
  def multiples_to limit
    return [] if factors.empty?
    (0...limit).select{|val| multiple_of_factor? val }
  end

  def multiple_of_factor? val
    !!factors.find{|factor| val%factor == 0 }
  end
end
