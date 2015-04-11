class PrimeFactors

  def self.for(n)
    return [] if n == 1
    factor_out([n])
  end

  def self.factor_out(arr)
    (2...arr[-1]).each do |divisor|
      if arr[-1] % divisor == 0
        last = arr.pop
        arr << divisor << last / divisor
        factor_out(arr)
        break
      end
    end
    arr
  end
end
