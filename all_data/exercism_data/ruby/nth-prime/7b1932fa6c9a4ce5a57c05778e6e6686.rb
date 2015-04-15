class Prime
  def nth(num)
    primes = [2]
    raise ArgumentError, 'Amount is not valid' if num < 1
    if num == 1
      out = 2
    else
      x = 3
      num.times do |index|
        prime_loop = false
        while prime_loop == false do
          if is_prime(primes, x) == true
            primes << x
            prime_loop = true
          else
            x = x + 1
          end
        end
      end
      primes.pop
      out = primes.last
    end
    out
  end
  def is_prime(primes, num)
    out = true
    primes.each do |pn|
      out = false if num % pn == 0
    end
    out
  end
end
 
