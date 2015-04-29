class PrimeFactors
  def self.for(num)
    bin = [num]
    while bin.any? {|n| not_prime?(n)}
      bin.map! {|n| factorize(n)}
      bin.flatten!
    end
    bin.delete_if {|n| n < 2}
    bin
  end

  def self.not_prime?(n)
    i = 2
    while i < n && n > 2
      return true if n % i == 0
      i += 1
    end
    false
  end

  def self.factorize(n)
    if not_prime?(n)
      fact = (2..(n / 2)).find {|num| n % num == 0}
      factor = n / fact
      [fact, factor]
    else
      n
    end
  end
end
