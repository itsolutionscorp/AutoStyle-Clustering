class Raindrops
  PRIMES = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}

  def self.convert(number)
    if prime_factors_for(number).join.empty? 
      number.to_s 
    else
      prime_factors_for(number).join
    end
  end

  def self.prime_factors_for(number)
    PRIMES.map do |prime, output|
      output if number.modulo(prime).zero?
    end
  end
end
