class Raindrops

  def self.convert(number)
    rain_string = ""
    rain_string += "Pling" if number.prime_facts.include?(3)
    rain_string += "Plang" if number.prime_facts.include?(5)
    rain_string += "Plong" if number.prime_facts.include?(7)
    rain_string = number.to_s if rain_string == ""
    rain_string
  end

end

class Fixnum

  def prime_facts(facts = [])
    (2...self).each do |num|
      if self % num == 0
        facts << num
        divisor = self / num
        return divisor.prime_facts(facts)
      end
    end
    facts << self
  end

end
