class Prime
  def self.nth(target)
    raise ArgumentError, 'Argument must be a positive integer.' unless target > 0 && target % 1 == 0
    primes_found = 1
    val_to_check = 2
    until primes_found == target do
      val_to_check += 1
      factor_found = false
      factor = 2
      while factor_found == false && factor <= val_to_check ** 0.5
        factor_found = true if val_to_check % factor == 0
        factor += 1
      end
      primes_found += 1 if !factor_found
    end
    return val_to_check
  end
end
