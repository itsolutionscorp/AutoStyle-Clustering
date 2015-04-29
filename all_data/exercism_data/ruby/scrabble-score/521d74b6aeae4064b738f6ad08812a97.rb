class Scrabble
  DICTIONARY = {  
    1 => [ "E", "A", "I", "O", "N", "R", "T", "L", "S", "U" ],
    2 => [ "D", "G"],
    3 => [ "B", "C", "M", "P" ],
    4 => [ "F", "H", "V", "W", "Y"],
    5 => [ "K"],
    8 => [ "J", "X" ],
    10 => [ "Q", "Z" ]
  }

  private_constant :DICTIONARY
  define_singleton_method(:score) { |str| Scrabble.new(str).score }

  def initialize str
    str = "" if str.nil?
    @str = str.chomp.strip
  end

  def score
    @str.chars.inject(0) do |score, char|
      DICTIONARY.each {|key, value| score+=key if value.include? char.upcase }
      score
    end
  end

end
