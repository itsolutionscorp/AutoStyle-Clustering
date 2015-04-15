class Raindrops
  class RaindropNumber < Struct.new(:number)
    DIVIDER_CONFIGURATION = {3 => "Pling", 5 => "Plang", 7 => "Plong"}
    
    def divided_by(divider)
      return number % divider == 0
    end
    
    def divider_string
      DIVIDER_CONFIGURATION.reduce("") do |memo, (divider, divider_output)|
        memo += divider_output if self.divided_by(divider)
        memo
      end
    end
    
    def convert
      d_string = divider_string
      d_string.length == 0 ? "#{number}" : d_string
    end
  end

  
  def self.convert(number)
    RaindropNumber.new(number).convert  
  end
end
