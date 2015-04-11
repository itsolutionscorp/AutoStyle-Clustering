# rubocop:disable Documentation, TrailingWhitespace
class Luhn
  def initialize(value)
    @value = value
  end
  
  def addends
    reverse_digits = @value.to_s.chars.reverse.map(&:to_i)
    
    reverse_digits.map.with_index do |d, idx|
      idx.odd? ? luhn_double(d) : d
    end.reverse
  end
  
  def checksum
    addends.reduce :+
  end
  
  def valid?
    checksum % 10 == 0
  end
  
  def self.create(value_without_checkdigit)
    base = value_without_checkdigit * 10
    checkdigit = (10 - Luhn.new(base).checksum % 10) % 10
    base + checkdigit
  end
  
  private 
  
  def luhn_double(d)
    d *= 2
    d > 9 ? d - 9 : d
  end
end
