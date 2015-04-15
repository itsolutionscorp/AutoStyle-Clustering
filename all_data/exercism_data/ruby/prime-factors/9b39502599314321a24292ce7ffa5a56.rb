module PrimeFactors

  def self.for nr
    res = []
    2.upto(nr) do |divisor|
      while nr >= divisor && nr % divisor == 0
        res << divisor
        nr /= divisor
      end
      break if nr == 1
    end
    res
  end
end
