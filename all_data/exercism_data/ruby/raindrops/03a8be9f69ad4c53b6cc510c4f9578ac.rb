class Raindrops

  def self.convert(test_num)
    factors = get_prime_factors(test_num)
    rain_speak = get_rain_speak(factors)
    return test_num.to_s if rain_speak.empty?
    rain_speak
  end

  private

  def self.get_prime_factors(num)
    factors = []
    for x in (2..num)
      while (num % x == 0)
        factors << x
        num /= x
      end
    end
    factors
  end

  def self.get_rain_speak(factors)
    rain_string = ""

    rain_string << "Pling" if factors.include?(3)
    rain_string << "Plang" if factors.include?(5)
    rain_string << "Plong" if factors.include?(7)

    rain_string
  end
end
