module Raindrops
  KEY_FACTORS = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert(number)
    factors = key_factors_of(number)
    return number.to_s if factors.nil? || factors.empty?
    factors.map{|x| KEY_FACTORS[x]}.join
  end

  def self.key_factors_of(num)
    KEY_FACTORS.keys.select { |n| num % n == 0}
  end
end
