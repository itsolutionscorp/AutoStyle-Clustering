class Raindrops
  PRIME_FACTORS = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}

  def self.convert(num)
    output = prime_factors_array.each_with_object('') do | (prime_factor, sound), result |
      result << sound if num % prime_factor == 0
    end
    output == '' ? num.to_s : output
  end

  def self.prime_factors_array
    PRIME_FACTORS.sort_by{ | k, v | k }
  end

end
