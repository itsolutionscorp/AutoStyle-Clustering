class Prime
  class << self
    def nth(number)
      fail ArgumentError if number <= 0
      generate_primes_till(number)[number - 1]
    end

    private

    def generate_primes_till(nth_number)
      sieve = initialize_sieve nth_number
      process_and_grow_sieve! sieve, nth_number
      extract_primes(sieve)
    end

    def initialize_sieve(sieve_size)
      [false, false] + Array.new(sieve_size - 1, true)
    end

    def process_and_grow_sieve!(sieve, num_primes_required)
      loop do
        process_sieve sieve
        break if extract_primes(sieve).size >= num_primes_required
        grow_sieve! sieve, num_primes_required
      end
    end

    def process_sieve(sieve)
      (1..sieve.size).each do |num|
        if sieve[num]
          (2 * num..sieve.size).step(num) do |foo|
            sieve[foo] = false
          end
        end
      end
    end

    def grow_sieve!(sieve, size)
      sieve.push(*Array.new(size + 1, true))
    end

    def extract_primes(sieve)
      sieve.map.with_index { |x, i| i if x }.compact
    end
  end
end
