class Raindrops
  PRIMES = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}

  def self.convert(number)
    prime_factors_for(number).any? ? prime_factors_for(number).join : number.to_s
  end

  def self.prime_factors_for(number)
    PRIMES.map { |prime, output| output if number.modulo(prime).zero? }
  end
end
