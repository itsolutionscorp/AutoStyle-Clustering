class Prime
  def Prime.nth(n)
    raise ArgumentError if n.class != Fixnum || n < 1
    primes = [2]
    counter =3
    until primes.size == n
      primes.each do |p|
        if counter % p == 0
          counter +=1
          break
        elsif p > Math.sqrt(counter)
          primes << counter
          counter += 1
          break
        end
      end
    end
    primes.last
  end

end
