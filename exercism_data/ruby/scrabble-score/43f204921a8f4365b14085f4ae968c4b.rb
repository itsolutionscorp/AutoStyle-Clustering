class Scrabble

  SCORING = {"a"=>1, "e"=>1, "i"=>1, "o"=>1, "u"=>1, "l"=>1, "n"=>1, "r"=>1, 
             "s"=>1, "t"=>1, "d"=>2, "g"=>2, "b"=>3, "c"=>3, "m"=>3, "p"=>3, 
             "f"=>4, "h"=>4, "v"=>4, "w"=>4, "y"=>4, "k"=>5, "j"=>8, "x"=>8, 
             "q"=>10, "z"=>10 }

  def initialize(word)
    @word       = word
    @valid_word = format(word)
  end

  # convenient scoring
  def self.score(word)
    new(word).score
  end

  def score
    @valid_word.chars.inject(0) { |sum,char| sum + SCORING[char] }
  end

  private

  def format(word)
    word.nil? ? "" : word.gsub(/\W/,"").downcase
  end
end
