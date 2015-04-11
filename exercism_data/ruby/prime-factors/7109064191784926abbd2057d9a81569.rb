class PrimeFactors
  def self.for(number)
    return [] if number == 1
    sqrt = (number**0.5).ceil
    factor = (2..sqrt).find{|div| number % div == 0}
    factor ? [factor] + self.for(number/factor) : [number]
  end
end
