class Prime
  def self.nth(target)
    raise ArgumentError, 'Argument must be a positive integer.' unless target > 0 && target % 1 == 0
    primes_found = 1
    val_to_check = 2
    while primes_found < target do
      val_to_check += 1
      primes_found += 1 if (2..(val_to_check/2).floor+1).map { |j| val_to_check % j }.min > 0
    end
    return val_to_check
  end
end

#483 secs compute time for the big prime.
