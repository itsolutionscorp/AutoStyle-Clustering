class Raindrops
  def self.convert(number)
    factors = factors_for(number)

    "".tap do |string|
      string << "Pling" if factors.include?(3)
      string << "Plang" if factors.include?(5)
      string << "Plong" if factors.include?(7)

      string << number.to_s if string == ""
    end
  end

  def self.factors_for(number)
    [].tap do |factors|
      until number == 1
        candidates = (2..number).to_a

        candidates.each do |candidate|
          if number % candidate == 0
            factors << candidate
            number = number / candidate
            break
          end
        end #   sick climb
      end # \o
    end # ,__/>
  end #     <"
end #       `
