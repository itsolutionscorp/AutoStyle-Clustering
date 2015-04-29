class Raindrops

  def self.convert(number)
    prime_factor = [3, 5, 7]
    drops = ""

    prime_factor.each do |factor|
      if number % factor == 0
        case factor
          when 3
            drops << "Pling"
          when 5
            drops << "Plang"
          when 7
            drops << "Plong"
          else
        end
      end
    end
    if drops == ""
      drops = number.to_s
    end
    drops
  end
end
