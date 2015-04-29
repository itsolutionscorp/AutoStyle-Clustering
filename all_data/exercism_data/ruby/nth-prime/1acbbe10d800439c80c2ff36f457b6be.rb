include Math

class Prime
  def self.nth(number)
    fetch_nth_prime(number)
  end

  def self.fetch_nth_prime(nth)
    fail ArgumentError if nth == 0
    @prime_list ||= prime_list = []
    generate_primes_list(nth) if @prime_list.length < nth
    @prime_list[nth - 1]
  end

  def self.generate_primes_list (nth)
    @prime_list = [2, 3, 5, 7]
    9.step(210_000, 2) do |dividend|
      0.upto(@prime_list.length) do |x|
        if dividend % @prime_list[x] == 0
          break
        elsif @prime_list[x] >= sqrt(dividend)
          @prime_list << dividend
          break
        end
      end
      break if @prime_list.length > nth
    end
    @prime_list
  end
end
