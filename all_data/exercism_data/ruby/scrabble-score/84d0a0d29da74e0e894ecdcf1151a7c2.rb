class Scrabble
  def initialize(letters)
    @letters = letters
  end

  def self.score(letters)
    new(letters).score
  end

  def score
    score_key = {
      "A" => 1, "B" => 3, "C" => 3, "D" => 2, "E" => 1, "F" => 4,
      "G" => 2, "H" => 4, "I" => 1, "J" => 8, "K" => 5, "L" => 1,
      "M" => 3, "N" => 1, "O" => 1, "P" => 3, "Q" => 10, "R" => 1,
      "S" => 1, "T" => 1, "U" => 1, "V" => 4, "W" => 4, "X" => 8,
      "Y" => 4, "Z" => 10, }
    score = 0

    if @letters != nil && (@letters != @letters.upcase || @letters != @letters.downcase)
      letter_array = @letters.split(//)
      i=0
      while i<letter_array.length
        score = score + score_key[letter_array[i].upcase]
        i+=1
      end
    end
    score
  end
end
