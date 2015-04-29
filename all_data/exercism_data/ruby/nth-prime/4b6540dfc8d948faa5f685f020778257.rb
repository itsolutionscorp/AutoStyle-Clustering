# first iteration without using Prime class

class Prime

  def self.nth(num)
    if num <= 0
      raise ArgumentError
    end
    primes = Prime.find_primes(104745)
    primes[num-1]
  end

private
  def self.find_primes(num)
    arr_of_primes = []
    (2..num).each do |num|
      if (2..num-1).all? { |denom| num%denom > 0 }
        arr_of_primes << num
      end
    end
    return arr_of_primes
  end
end
