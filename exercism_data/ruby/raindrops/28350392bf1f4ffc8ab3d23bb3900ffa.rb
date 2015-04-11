class Raindrops

  PRIMES = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}

  def self.convert number
    output = PRIMES.select { |prime, _| number % prime == 0 }
    output.empty? ? number.to_s : output.values.join
  end
end
