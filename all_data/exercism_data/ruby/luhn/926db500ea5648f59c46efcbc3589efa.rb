class Luhn
  def self.create number
    number*10 + Luhn.new(number*10).checksum*9%10
  end
  
  attr_reader :number
  def initialize number
    @number = number
  end
  
  def addends
    @addends ||= reverse_digit_map_with_index{ |d,i| addend_digit d, i }
  end
  
  def checksum
    @checksum ||= addends.reduce(:+)
  end
  
  def valid?
    checksum%10 == 0
  end
  
  private
    def reverse_digit_map_with_index &block
      digits.reverse.map.with_index do |digit,index|
        yield digit, index
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
