class Raindrops
  def self.convert number
    drops = []
    drops_by_prime = {
      3 => 'Pling',
      5 => 'Plang',
      7 => 'Plong'
    }
    drops = drops_by_prime.keys.select { |prime| contains_prime_factor?(number, prime) }.map { |prime| drops_by_prime[prime] }
    drops = [number.to_s] if drops.empty?
    drops.join ''
  end

  private 

  def self.contains_prime_factor? number, prime
    number % prime == 0
  end
end
