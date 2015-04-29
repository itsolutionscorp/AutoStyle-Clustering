class Raindrops
  def self.convert(i)
    factors = PrimeFactors.of i
    result = ''
    result += 'Pling' if factors.include? 3
    result += 'Plang' if factors.include? 5
    result += 'Plong' if factors.include? 7
    result = i.to_s if result == ''
    result
  end
end

class PrimeFactors
  def self.of(n)
    factors = []
    factor = 2
    while n > 1
      while (n % factor == 0)
        factors.push factor
        n /= factor
      end
      factor += 1
    end
    factors
  end
end
