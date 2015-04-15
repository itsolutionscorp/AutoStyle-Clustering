class Scrabble

  LETTER_VALUES = {
    1 => ["A","E","I","O","U","L","N","R","S","T"],
    2 => ["D","G"],
    3 => ["B","C","M","P"],
    4 => ["F","H","V","W","Y"],
    5 => ["K"],
    8 => ["J","X"],
    10 => ["Q","Z"]
  }

  def self.score(word)
    Scrabble.new(word).score
  end

  def initialize(word)
    @word = word.nil? ? "" : word.gsub(/[^a-zA-Z]/,"")
  end

  def score
    LETTER_VALUES.inject(0) do |sum, (score, letters)|
      sum + score * @word.chars.count {|i| letters.include?(@word[i].upcase)}
    end
  end
end
