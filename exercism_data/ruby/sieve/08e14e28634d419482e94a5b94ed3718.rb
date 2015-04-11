class Sieve
  
  attr_reader :num

  def initialize(num)
    @num = num
  end

  def primes
    [*(2..num)].select { |n| is_prime?(n) }
  end


  def is_prime?(num)
    return false if num <= 1
    2.upto(Math.sqrt(num)) do |x|
      return false if num % x == 0
    end
    true
  end  

end
