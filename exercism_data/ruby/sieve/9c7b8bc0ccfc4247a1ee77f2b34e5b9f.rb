class Sieve
  
  def initialize(number)
    @number = number
  end

  def primes
    @primes = (2..@number).to_a.delete_if { |num|  !prime?(num) }
  end
  
  private

  def prime?(number)
    for deviser in 2..(number - 1)
      return false if (number % deviser) == 0
    end
    true
  end

end
