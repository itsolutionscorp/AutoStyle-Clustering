class Sieve
  attr_reader :primes

  def initialize num_limit
    @limit = num_limit  
  end

  def primes
    @primes ||= get_primes 
  end
  
  private
  
  def get_primes
    primes = []
    current_prime = 2
    multiples = []
    until current_prime > @limit 
      multiples += get_multiples current_prime, @limit
      primes.push(current_prime)
      current_prime = get_next_prime multiples, current_prime
    end
    primes
  end
  
  def get_multiples current_prime, limit
    (1..limit/current_prime).to_a.map { |multiple| multiple * current_prime }
  end

  def get_next_prime multiples, current_prime
    next_prime = current_prime + 1
    while multiples.include? next_prime
      next_prime += 1
    end
    next_prime
  end
end
