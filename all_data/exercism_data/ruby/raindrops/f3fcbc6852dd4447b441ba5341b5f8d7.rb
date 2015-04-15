require 'Prime'

class Raindrops
  def self.convert(numb)
    rain = ''
    facts = Prime.prime_division(numb)
    facts.each do |fact|
      rain += "Pling" if (fact[0] == 3)
      rain += "Plang" if (fact[0] == 5)
      rain += "Plong" if (fact[0] == 7)
    end
    rain = numb.to_s if (rain == '')
    rain
  end
end
