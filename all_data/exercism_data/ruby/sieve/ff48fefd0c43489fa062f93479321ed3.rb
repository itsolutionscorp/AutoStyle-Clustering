class Sieve
  def initialize(n)
    @num = n
    @result = (2..@num).each_with_object([]){|i, a| a << i}
  end

  def primes
    @result.each{|i| (i..@num).each{|y| @result.delete(i*y)}}
  end
end
