class Raindrops
  def convert(number)
    factors = prime_factors(number)
    string = ""
    string << "Pling" if factors.include?(3)
    string << "Plang" if factors.include?(5)
    string << "Plong" if factors.include?(7)
    string.empty? ? number.to_s : string
  end

  private
  def prime_factors(number)
    primes = []
    candidate = 2
    while number > 1
      while number % candidate == 0
        primes << candidate
        number /= candidate
      end
      candidate += 1
    end
    primes.uniq
  end
end
