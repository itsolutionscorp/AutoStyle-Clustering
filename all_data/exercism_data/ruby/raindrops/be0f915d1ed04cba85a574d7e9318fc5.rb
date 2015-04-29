class Raindrops
  def self.convert(number)
    prime_factor = SmallPrimeFactor.new(number)
    response = ''
    response << 'Pling' if prime_factor.include?(3)
    response << 'Plang' if prime_factor.include?(5)
    response << 'Plong' if prime_factor.include?(7)
    response.empty? ? number.to_s : response
  end
end

class SmallPrimeFactor
  def initialize(number)
    @primes = []
    [3, 5, 7].each do |prime|
      @primes << prime if number % prime == 0
    end
  end

  def include?(number)
    @primes.include?(number)
  end
end
