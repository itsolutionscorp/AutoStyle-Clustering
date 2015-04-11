class Raindrops
  def self.convert(number)
    factors = []
    original_number = number

    while number > 1
      if number % 3 == 0
        number = number/3
        factors << 'Pling' unless factors.include?('Pling')
      elsif number % 5 == 0
        factors << 'Plang' unless factors.include?('Plang')
        number = number/5
      elsif number % 7 == 0
        factors << 'Plong' unless factors.include?('Plong')
        number = number/7
      elsif number % 2 == 0
        number = number/2
      else
        return original_number.to_s
      end
    end

    if factors.empty?
      return original_number.to_s
    else
      return factors.join("")
    end
  end
end
