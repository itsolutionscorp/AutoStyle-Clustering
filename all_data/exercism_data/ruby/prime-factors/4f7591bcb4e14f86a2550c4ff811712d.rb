class PrimeFactors
  def self.for(num)
    n = 2

    [].tap do |res|
      while num > 1 do
        if 0 == num % n
          res << n
          num /= n
        else
          n += 1
        end
      end
    end
  end
end
