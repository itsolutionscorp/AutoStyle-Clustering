class Scrabble

  LETTER_VALUES = {}

  def self.score(word)
    Scrabble.new(word).score
  end

  def initialize(word)
    @word = word.nil? ? "" : word.gsub(/[^a-zA-Z]/,"")
    generate_letter_values()
  end

  def score
    @word.chars.inject(0){|sum, char| sum += LETTER_VALUES[char.upcase]}
  end

  private

  def generate_letter_values
    raw = [
      [1,"A","E","I","O","U","L","N","R","S","T"],
      [2,"D","G"],
      [3,"B","C","M","P"],
      [4,"F","H","V","W","Y"],
      [5,"K"],
      [8,"J","X"],
      [10,"Q","Z"]
    ]
    raw.each do |serie|
      serie.each_with_index do |char, index|
        LETTER_VALUES[char] = serie[0] unless index == 0
      end
    end
  end

end
