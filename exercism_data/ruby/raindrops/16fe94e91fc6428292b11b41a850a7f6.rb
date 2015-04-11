class Raindrops
  
  FACTOR_SOUND_HASH = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}

  def self.convert(number)
    factors = find_relevant_factors(number)
    response = factors.map do |factor|
      FACTOR_SOUND_HASH[factor]
    end.compact
    "#{response.size > 0 ? response.join : number}" 
  end

  private 

  def self.find_relevant_factors(number)
    max = FACTOR_SOUND_HASH.keys.max
    (1..max).select { |n| (number % n).zero? } 
  end
end
