class Sieve

  def initialize(max_number)
    @potential_primes = (2..max_number).to_a
  end

  def primes(remaining_nums = @potential_primes, found_primes = [])

    if remaining_nums.length.zero?
      found_primes
    else
      found_primes.push(remaining_nums.shift)
      remaining_nums.select!{|num| !(num % found_primes.last).zero?}
      primes(remaining_nums, found_primes)
    end

  end

end
