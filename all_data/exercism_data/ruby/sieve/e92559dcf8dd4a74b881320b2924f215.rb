class Sieve

  def initialize(upper_end)
    @unmarked_numbers = (3..upper_end).to_a
  end

  def primes
    if @primes.nil?
      @primes = [2]
      sieve_numbers while @unmarked_numbers.size > 0
    end
    @primes
  end

  private

  def sieve_numbers
    current_factor = @primes.last
    @unmarked_numbers = @unmarked_numbers.find_all{|number|
      number % current_factor != 0
    }
    @primes << @unmarked_numbers.shift
  end
end
