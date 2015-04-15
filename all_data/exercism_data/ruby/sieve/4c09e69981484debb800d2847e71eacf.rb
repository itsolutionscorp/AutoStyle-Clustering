class Sieve
  attr_reader :num

  def initialize num
    @num = num
  end

  def primes
    (1..num).select{ |x| check_prime? x}
  end

  private

  def check_prime? x
    2.upto(Math.sqrt(x).to_i) { |n| return false if x % n == 0 }
    x == 1 ? false : true
  end

end
