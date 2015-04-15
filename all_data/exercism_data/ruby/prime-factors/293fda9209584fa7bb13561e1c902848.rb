class PrimeFactors

  def self.for(number)
    factors = []
    i = 1

    while i <= (number)
      if number % i == 0 && prime?(i)
        factors << i
        number = number / i
      else
        i += 1
      end
    end

    factors
  end

  private

  def self.prime?(n)
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
