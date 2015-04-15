require 'mathn'

class Raindrops
  def self.convert(num)
    rain_lang = num.prime_division.flatten.reduce("") { |rainian, cur|
      case cur
      when 3
        rainian << "Pling"
      when 5
        rainian << "Plang"
      when 7
        rainian << "Plong"
      end
      rainian
    }
    rain_lang.empty? ? num.to_s : rain_lang
  end
end
