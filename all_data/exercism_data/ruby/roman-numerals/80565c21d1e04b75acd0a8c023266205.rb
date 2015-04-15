class RomanNumerable < Struct.new(:number)
  ROMAN_CONFIGURATION = {
    "Millenian" => {:previous => "C", :base => "M", :mid => nil, :next_class => nil, :divider => 1000},
    "Centenian" => {:previous => "X", :base => "C", :mid => "D", :next_class => "Millenian", :divider => 100},
    "Decenian" => {:previous => "I", :base => "X", :mid => "L", :next_class => "Centenian", :divider => 10},
    "Unit" => {:previous => nil, :base => "I", :mid => "V", :next_class => "Decenian", :divider => 1} 
  }
  
  class UnitNumber < Struct.new(:number, :unit_configuration)
    def to_roman
      return unit_configuration[:previous] + unit_configuration[:base] if number < 0 
      
      return "" if number == 0
      return unit_configuration[:base] * number if number < 4
      return unit_configuration[:base] + unit_configuration[:mid] if number == 4
      return unit_configuration[:mid] if number == 5
      return unit_configuration[:mid] + unit_configuration[:base] * (number - 5) if number < 9
      
      return UnitNumber.new(-1, ROMAN_CONFIGURATION[unit_configuration[:next_class]]).to_roman
    end
  end 
  
  class 

  
  def split_deciles    
    output = %w[Millenian Centenian Decenian Unit].reduce({:running_count => number, :deciles => []}) do |memo, unit_type|
      divider = ROMAN_CONFIGURATION[unit_type][:divider] 
      unit_count = memo[:running_count] / divider
      
      memo[:running_count] = memo[:running_count] - unit_count * divider 
      memo[:deciles].push(UnitNumber.new(unit_count, ROMAN_CONFIGURATION[unit_type] ))
      memo
    end

    output[:deciles]
  end
  
  def to_roman 
    return number if number > 3000
    split_deciles.map{|d| d.to_roman}.join("")
  end
end


class Fixnum
  def to_roman 
    RomanNumerable.new(self).to_roman
  end
end
