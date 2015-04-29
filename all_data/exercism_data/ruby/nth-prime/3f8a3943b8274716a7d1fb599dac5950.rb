class Prime
  def self.nth(n)
    raise ArgumentError if n == 0
    find_primes(n).last
  end

  private

  def self.find_primes(n)
    primes = []
    counter = 2
    while primes.length != n do
      is_prime = true
      divisor = counter ** 0.5
      (2..divisor).to_a.each do |div|
        is_prime = false if counter % div == 0
      end
      primes << counter if is_prime
      counter += 1
    end
    primes
  end
end
