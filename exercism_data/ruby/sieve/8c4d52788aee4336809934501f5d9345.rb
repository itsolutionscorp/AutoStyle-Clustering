class Sieve
  def initialize n
    @n = n
  end

  def primes
    sieve = (3..@n).step(2).to_a
    (3..(Math.sqrt(@n).to_i+1)).each do |i|
      sieve = sieve.reject{|k| k % i == 0 && k != i}
    end
    [2] | sieve
  end

end
