require "prime"

class Raindrops
  SOUND = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }
  
  attr_reader :source, :prime_factorization
  
  def initialize(source)
    @source = source
    @prime_factorization = source.prime_division.flatten
  end
  
  def available_factors
    @available_factors ||= @prime_factorization.uniq & SOUND.keys
  end
  
  def to_s
    if available_factors.any?
      available_factors.map{|n| SOUND[n]}.join
    else
      source.to_s
    end
  end
  
  def self.convert(number)
    new(number).to_s
  end
end
