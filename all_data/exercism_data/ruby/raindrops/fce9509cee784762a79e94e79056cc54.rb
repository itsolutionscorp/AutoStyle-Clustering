require "prime"

class Raindrops
  DICTIONARY = { 3 => 'Pling',
                 5 => 'Plang',
                 7 => 'Plong' }
  
  attr_reader :source, :translatable_factors
  
  def initialize(source)
    @source = source.to_i
    @translatable_factors ||= @source.prime_division.flatten.uniq & DICTIONARY.keys
  end
  
  def translation
    translatable_factors.map{|factor| DICTIONARY[factor]}.join
  end
  
  def to_s
    translatable_factors.any? ? translation : source.to_s 
  end
  
  # Class methods
  def self.convert(number)
    new(number).to_s
  end
end
