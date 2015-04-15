class Raindrops

  MATRIX = {pling: 3, plang: 5, plong: 7}

  def self.convert(number)
    result = ""
    original_number = number
    divisor = 2

    while number >= divisor
      if number.modulo(divisor).zero?
        if MATRIX.key(divisor)
          str = MATRIX.key(divisor).to_s.capitalize
          result << str unless result.include? str
        end
        number = number / divisor
      else
        divisor += 1
      end
    end
    
    result = original_number.to_s if result.empty?
    result
  end

end
