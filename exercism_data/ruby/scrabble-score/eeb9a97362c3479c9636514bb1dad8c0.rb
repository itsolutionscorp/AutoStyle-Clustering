class Scrabble

  LETTERS_PER_SCORE = {
    1 => ['A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T'],
    2 => ['D', 'G'],
    3 => ['B', 'C', 'M', 'P'],
    4 => ['F', 'H', 'V', 'W', 'Y'],
    5 => ['K'],
    8 => ['J', 'X'],
    10 => ['Q', 'Z']
  }

  def initialize(word)
    @word = word.strip if word
    @score_per_letter = transform(LETTERS_PER_SCORE)
  end

  def transform(letters_per_score_db)
    letters_per_score_db.each_with_object ({}) do |(score,letters), score_per_letter|
      letters.each do |a_letter|
        score_per_letter[a_letter.upcase] = score
      end
    end
  end


  def score
    return 0 unless @word
    @word.split("").reduce(0) do |sum,letter|
      sum + @score_per_letter[letter.capitalize]
    end
  end

  def self.score(word)
    Scrabble.new(word).score
  end

end
