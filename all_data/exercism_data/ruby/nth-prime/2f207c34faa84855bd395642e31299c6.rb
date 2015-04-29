class Prime
  INF = 1 / 0.0
  @@primes = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41]
  # Eliminates edge cases; allows J. Massias and G. Robin approximation for
  # Prime.approx_upper_bound

  def self.nth(nth_prime)
    raise ArgumentError unless (1..INF).cover?(nth_prime)

    generate_primes_if_less_than(nth_prime)
    @@primes[nth_prime - 1]
  end

  private

  def self.generate_primes_if_less_than(nth_prime)
    return if @@primes.length >= nth_prime  # Already have enough primes

    lower = @@primes.last + 1
    upper = approx_upper_bound(nth_prime)

    (lower..upper).each { |n| @@primes << n if n.is_prime? }
  end

  def self.approx_upper_bound(nth_prime)
    # Per J. Massias and G. Robin,
    # "Bornes effectives pour certaines fonctions concernant les nombres",
    # https://primes.utm.edu/howmany.html

    nth_prime * ( Math.log(nth_prime) +
                  Math.log(Math.log(nth_prime - 1)) +
                  (1.8 * Math.log(Math.log(nth_prime))/Math.log(nth_prime))
                )
  end
end

class Fixnum
  def is_prime?
    square_root = Math.sqrt(self).ceil
    self == 2 || (2..square_root).all? { |n| self % n != 0 }
  end
end
