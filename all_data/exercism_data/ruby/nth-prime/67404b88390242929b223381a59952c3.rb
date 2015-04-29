module Prime
  def self.nth desired_idx
    raise ArgumentError if desired_idx <= 0
    primes = [2]
    current_num = 2
    until primes.length.eql? desired_idx
      if is_prime? (current_num += 1), primes
        primes << current_num
      end
    end
    primes.last
  end

  def self.is_prime? candidate, known_primes
    return known_primes.none? {|n| candidate % n == 0 }
  end
end
