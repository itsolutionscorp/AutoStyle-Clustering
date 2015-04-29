class Prime

  def self.nth(index)
    raise ArgumentError if index == 0

    i, k = 0, 0
    until k == index
      k += 1 if check_prime(i)
      i += 1
    end
    i - 1

  end

  def self.check_prime(n)
    if n < 4
      lookups[n]
    elsif n.even?
      false
    else
      i = 3
      while i < (n * 0.5 + 1)
        return false if n % i == 0
        i += 2
      end
      true
    end
  end

  def self.lookups
    { 1 => false,
      2 => true,
      3 => true}
  end


end
