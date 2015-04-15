class Sieve
  attr_reader :primes, :up_to

  def initialize(to)
    @up_to = to
    @primes = [2]
    execute
  end

  def execute
    potential_primes = []

    # even numbers above 2 are not prime, so we avoid them entirely
    # and skip a loop
    (3..up_to).step(2) { |n| potential_primes.push(n) }

    until potential_primes.empty?
      current_prime = potential_primes.shift
      primes.push(current_prime)

      potential_primes.reject! do |potential_prime|
        potential_prime % current_prime == 0
      end
    end
  end
end
