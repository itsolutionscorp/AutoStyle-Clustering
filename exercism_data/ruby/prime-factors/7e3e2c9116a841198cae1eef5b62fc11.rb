class PrimeFactors
  def self.for number
    factors = []
    leftover = number
    (2..number).each do |div|
      break if leftover == 1
      if leftover % div == 0
        leftover /= div
        factors << div
        redo
      end
    end
    factors
  end
end
