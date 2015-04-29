class Sieve

  attr_accessor :numbers

  def initialize number
    @numbers = [ *2..number ]
  end

  def primes
    numbers.each do |number|
      remove_primes_of number
    end
  end

private

  def remove_primes_of number
    numbers.delete_if do |x| 
      ( x % number == 0 ) unless number == x
    end
  end

end
