class Sieve

  attr_accessor :numbers_array

  def initialize number
    @numbers_array ||= [ *2..number ]
  end

  def primes
    numbers_array.each do |number|
      remove_primes_of number
    end
  end

private

  def remove_primes_of number
    numbers_array.delete_if do |x| 
      ( x % number == 0 ) unless number == x
    end
  end

end
