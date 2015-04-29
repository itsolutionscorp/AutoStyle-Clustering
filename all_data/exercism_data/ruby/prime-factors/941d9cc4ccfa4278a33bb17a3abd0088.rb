class PrimeFactors

  def self.for(n)
    [].tap do |out|
      p = 2
      while n != 1
        if (n % p).zero?
          n /= p
          out << p
        else
          p += 1
        end
      end
    end
  end
end
