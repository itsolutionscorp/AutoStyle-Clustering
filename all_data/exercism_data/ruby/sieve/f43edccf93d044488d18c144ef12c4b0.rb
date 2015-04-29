class Sieve

  def initialize sieve
    @sieve = sieve
  end

  def primes
    list = (2..@sieve).to_a
    2.upto(@sieve).each do |i|
      list.delete_if { |n| n % i == 0 and n != i }
    end
    list
  end
end
