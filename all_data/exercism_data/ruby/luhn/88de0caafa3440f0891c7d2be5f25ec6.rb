class Luhn
  def self.create number
    number*10 + Luhn.new(number*10).checksum*9%10
  end
  
  attr_reader :number
  def initialize number
    @number = number
  end
  
  def addends
    @addends ||= generate_addends
  end
  
  def checksum
    @checksum ||= addends.reduce(:+)
  end
  
  def valid?
    checksum%10 == 0
  end
  
  private
    def generate_addends
      digits.reverse.map.with_index do |digit,index|
        addend_digit digit, index
      end.reverse
    end
  
    def addend_digit digit, position
      if position.odd?
        double_digit digit
      else
        digit
      end
    end
  
    def digits
      @digits ||= number.to_s.chars.map(&:to_i)
    end
  
    def double_digit digit
      digit*2 >= 10 ? digit*2-9 : digit*2
    end
end
