class PrimeFactors
  def self.for(number)
    factors = []
    sqrt = (number ** 0.5).ceil
    (2..sqrt).each_with_object(factors) do |divisor, list|
      if number % divisor == 0
        list << divisor
        number /= divisor
        redo
      end
    end
    number == 1 ? factors : factors << number
  end
end
