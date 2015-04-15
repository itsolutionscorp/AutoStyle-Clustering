class Raindrops
  RAINDROPS = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  def self.convert number
    raindrops = RAINDROPS.map do |prime, word|
      is_prime_factor?(number, prime) ? word : ""
    end.join
    raindrops.empty? ? number.to_s : raindrops
  end

  def self.is_prime_factor? number, prime
    number % prime == 0
  end

end
