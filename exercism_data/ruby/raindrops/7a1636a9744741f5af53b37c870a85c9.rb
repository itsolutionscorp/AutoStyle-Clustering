class Raindrops
  def self.convert(integer)
    prime_factors = prime_factors(integer)
    sound = ""
    if prime_factors.include?(3)
      sound << "Pling"
    end
    if prime_factors.include?(5)
      sound << "Plang"
    end
    if prime_factors.include?(7)
      sound << "Plong"
    end
    if sound == ""
      sound = integer.to_s
    end

    return sound
  end

  def self.prime_factors(integer)
    factors = []

    if is_prime?(integer)
      factors << integer
      return factors
    end

    counter = 2
    continue = true

    while continue do
      if is_prime?(counter)
        if (integer % counter) == 0
          continue = false
          factors << counter
          cofactor = integer / counter
          if is_prime?(cofactor)
            factors << cofactor
          else
            factors << prime_factors(cofactor)
          end
        else
          counter += 1
        end
      else
        counter += 1
      end
    end

    return factors.flatten
  end

  def self.is_prime?(integer)
    counter = 2
    while counter <= (integer / 2) do
      if (integer % counter) == 0
        return false
      end
      counter += 1
    end

    return true
  end
end
