class Raindrops

  @prime_factors = []
  @rain_sound = ""

  def self.convert(number)
    @prime_factors = []
    @rain_sound = ""
    return number.to_s if number % 3 != 0 && number % 5 != 0 && number % 7 != 0 #return number if doesn't have factors of 3, 5 or 7
    divide_by_3(number)
  end

  def self.divide_by_3(number)
    if number % 3 == 0
      @prime_factors << 3
      remainder = number / 3
      divide_by_3(remainder) #recursive function until the remainder isn't divisible by 3
    else
      divide_by_5(number)
    end
  end

  def self.divide_by_5(number)
    if number % 5 == 0
      @prime_factors << 5
      remainder = number / 5
      divide_by_5(remainder)
    else
      divide_by_7(number)
    end
  end

  def self.divide_by_7(number)
    if number % 7 == 0
      @prime_factors << 7
      remainder = number / 7
      divide_by_7(remainder)
    else
      make_raindrop_sounds(@prime_factors)
    end
  end

  def self.make_raindrop_sounds(array)
    array.uniq.map do |element|
      case element
      when 3
        @rain_sound << "Pling"
      when 5
        @rain_sound << "Plang"
      when 7
        @rain_sound << "Plong"
      end
    end
    @rain_sound
  end

end
