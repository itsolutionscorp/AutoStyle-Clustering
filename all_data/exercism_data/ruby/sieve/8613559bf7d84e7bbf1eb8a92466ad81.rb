class Sieve
  def initialize(n)
    @max = n
  end

  def primes
    @primes ||= filter_primes
  end

  private

  def filter_primes
    possibles = (2..@max).to_a
    prime_index = 0
    while prime_index < possibles.length
      prime = possibles[prime_index]
      new_possibles = possibles.slice(0,prime_index+1)
      check_index = prime_index + 1
      while check_index < possibles.length
        n = possibles[check_index]
        new_possibles.push(n) unless n % prime == 0
        check_index += 1
      end
      possibles = new_possibles
      prime_index += 1
    end
    possibles
  end

end
