class Raindrops
  def self.convert(number)
    array = []
    current_number = number
    return_string = ''
    while current_number >= 1
      if number%(current_number) == 0
        array.push current_number
      end
      current_number -= 1
    end

    if array.include?(3)
      return_string += "Pling"
    end

    if array.include?(5)
      return_string += "Plang"
    end

    if array.include?(7)
      return_string += "Plong"
    end

    if return_string == ''
      return_string += number.to_s
    end

    return_string
  end
end
