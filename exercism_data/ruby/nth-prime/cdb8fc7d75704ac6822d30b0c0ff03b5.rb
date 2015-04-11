class Prime
  def self.nth(n)
    fail ArgumentError if n < 1

    found_primes = []
    (2..Float::INFINITY).each do |candidate|
      next if found_primes.any? { |p| candidate.remainder(p).zero? }
      found_primes << candidate
      return candidate if found_primes.length == n
    end
  end
end
