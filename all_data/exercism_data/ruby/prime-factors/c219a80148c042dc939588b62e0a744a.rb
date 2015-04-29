class PrimeFactors
  def self.for(num)
    n = 2
    rest = num

    [].tap do |res|
      while rest > 1 do
        if 0 == rest % n
          res << n
          rest /= n
        else
          n += 1
        end
      end
    end
  end
end
