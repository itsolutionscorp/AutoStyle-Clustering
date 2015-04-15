class Sieve
  attr_reader :primes
  def initialize(n)
    @primes = retrieve_prime_numbers(n)
  end

  def retrieve_prime_numbers(n)
    numbers = Array.new(n, true)

    (2..n).each do |number|
      multiples = number
      until multiples > n
        multiples += number
        numbers[multiples] = false if multiples <= n
      end
    end

    numbers.each_index.select { |index| numbers[index] == true }.reject { |index| index == 0 || index == 1 }
  end
end
