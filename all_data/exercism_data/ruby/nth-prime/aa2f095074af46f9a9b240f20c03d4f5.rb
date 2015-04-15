module Prime

  @@known_primes = [2]

  def self.nth desired_idx
    raise ArgumentError if desired_idx <= 0
    verify_primes_for_index desired_idx
    @@known_primes[desired_idx - 1]
  end

  def self.verify_primes_for_index idx
    current_num = @@known_primes.last
    until @@known_primes.count > idx
      if is_prime? (current_num += 1)
        @@known_primes << current_num
      end
    end
  end

  def self.is_prime? candidate
    return @@known_primes.none? { |p| candidate % p == 0 }
  end
end
