module Prime

  @@known_primes = [2]

  def self.nth desired_idx
    raise ArgumentError if desired_idx <= 0
    calculate_n_primes desired_idx
    @@known_primes[desired_idx - 1]
  end

  def self.calculate_n_primes idx
    current_num = @@known_primes.last
    until @@known_primes.count > idx
      if prime? (current_num += 1)
        @@known_primes << current_num
      end
    end
  end

  def self.prime? candidate
    limit = Math.sqrt(candidate).ceil
    @@known_primes.each do |p|
      return true  if p > limit
      return false if candidate % p == 0
    end
  end
end
