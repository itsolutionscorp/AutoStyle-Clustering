module PrimeFactors
  def self.for(nb)
    factors, candidate = [], 2
    while nb >= candidate
      if nb % candidate == 0
        factors << candidate
        nb /= candidate
      else
        candidate += 1
      end
    end
    factors
  end
end
