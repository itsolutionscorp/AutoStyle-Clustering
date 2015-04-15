class RainCollector
  def initialize(water="")
    @water = water
  end

  def and_if(it_is_true, drop)
    @water += drop if it_is_true 
    RainCollector.new(@water)
  end

  def pour(n)
    @water.empty? ? n.to_s : @water
  end
end

class Raindrops
  def self.convert(n)
    RainCollector.new
      .and_if( has_prime_factor(n, 3), "Pling")
      .and_if( has_prime_factor(n, 5), "Plang")
      .and_if( has_prime_factor(n, 7), "Plong")
      .pour(n)
  end

  private

  def self.has_prime_factor(n, prime)
    n % prime == 0  
  end
end
