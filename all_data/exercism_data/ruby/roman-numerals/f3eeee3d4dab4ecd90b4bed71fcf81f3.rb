class Roman
  attr_accessor :number
  ROMAN_DICTIONARY = {
    1 => 'I',
    2 => 'II',
    3 => "III",
    4 => "IV",
    5 => "V",
    6 => "VI",
    7 => "VII",
    8 => "VIII",
    9 => "IX",
    10 => "X"    
  }
  def initialize(number)
    
  end
  
  def self.to_roman
    number
  end
end
