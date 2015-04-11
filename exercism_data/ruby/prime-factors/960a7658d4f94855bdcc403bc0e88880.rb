module PrimeFactors

  def self.for(n)
    return [] if n < 2
    x = (2..n/2).find { |y| (n % y).zero? }
    x ? [x, *self.for(n/x)] : [n]
  end

end
