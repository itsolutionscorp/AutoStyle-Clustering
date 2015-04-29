class PrimeFactors
  def self.for number
    self.new(number).factors
  end
  
  attr_reader :value
  def initialize value
    @value = value
  end
  
  def factors
    @factors ||= generate_factors value
  end
  
  private
  def generate_factors number
    factors = []
    factor = 2
    while number > 1
      while is_factor number, factor
        factors << factor
        number /= factor
      end
      factor+=1
    end
    factors
  end
  
  def is_factor number, factor
    number%factor==0
  end
end
