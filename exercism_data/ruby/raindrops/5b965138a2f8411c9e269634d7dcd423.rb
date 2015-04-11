class Raindrops
  PRIME_FACTORS = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}

  def self.convert(num)
    output = PRIME_FACTORS.keys.sort.each_with_object('') do | prime_factor, result |
      result << PRIME_FACTORS[prime_factor] if num % prime_factor == 0
    end
    output = num.to_s if output == ''
    output
  end

end
