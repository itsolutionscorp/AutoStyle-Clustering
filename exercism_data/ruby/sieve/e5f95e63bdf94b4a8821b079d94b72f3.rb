class Sieve
  def initialize(lim)
    @limit = lim
  end

  def primes
    natural_numbers = (2..@limit).to_a

    (2..@limit).each do |test_num|
      natural_numbers.each do |nat_num|
        natural_numbers.delete(nat_num) if (nat_num % test_num == 0 && nat_num != test_num)
      end
    end

    natural_numbers
  end
end
