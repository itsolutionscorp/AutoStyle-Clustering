class ETL
  def self.transform(legacy_data)
    scrabble_scores = ScrabbleScores.new
    legacy_data.each do |score, letters|
      scrabble_scores.add_letters(letters, score)
    end
    scrabble_scores.to_hash
  end
end

class ScrabbleScores
  attr_reader :letters

  def initialize
    @letters = []
  end

  def add_letters(letters, score)
    letters.each do |letter|
      self.letters << ScrabbleLetter.new(letter, score)
    end
  end

  def to_hash
    hash = {}
    letters.each do |letter|
      hash.update(letter.to_hash)
    end
    hash
  end
end

class ScrabbleLetter
  attr_reader :letter, :score

  def initialize(letter, score)
    @letter = letter.downcase
    @score = score
  end

  def to_hash
    hash = {}
    hash[letter] = score
    hash
  end
end
