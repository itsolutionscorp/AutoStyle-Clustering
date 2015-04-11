class RomanNumerable < Struct.new(:number)
  ROMAN_CONFIGURATION = [
    {:previous => nil, :base => "I", :mid => "V"},
    {:previous => "I", :base => "X", :mid => "L"},
    {:previous => "X", :base => "C", :mid => "D"},
    {:previous => "C", :base => "M", :mid => nil}
  ]
  
  class UnitNumber < Struct.new(:number, :unit_configuration, :index)
    def to_roman
      return unit_configuration[:previous] + unit_configuration[:base] if number < 0 
      
      return "" if number == 0
      return unit_configuration[:base] * number if number < 4
      return unit_configuration[:base] + unit_configuration[:mid] if number == 4
      return unit_configuration[:mid] if number == 5
      return unit_configuration[:mid] + unit_configuration[:base] * (number - 5) if number < 9
      
      return UnitNumber.new(-1, ROMAN_CONFIGURATION[index + 1]).to_roman
    end
  end  
  
  def split_deciles
    numbers = number.to_s.chars  
    ROMAN_CONFIGURATION.count.times.map do |t|
      UnitNumber.new(numbers.pop.to_i, ROMAN_CONFIGURATION[t], t)
    end  
  end
  
  def to_roman 
    return number if number > 3000
    split_deciles.reverse.map{|d| d.to_roman}.join("")
  end
end


class Fixnum
  def to_roman 
    RomanNumerable.new(self).to_roman
  end
end
