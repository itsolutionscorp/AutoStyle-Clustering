require 'pry'
class Binary


  def initialize str
    @stream = str.chars
  end

  def to_decimal
    return 0 if !@stream.all? { |d| d=='1' || d=='0' }
    decimal = 0
    @stream.each do |digit|
      digit = (digit == '1') ? 1 : 0
      decimal = decimal*2 + digit
    end
    decimal
  end
end