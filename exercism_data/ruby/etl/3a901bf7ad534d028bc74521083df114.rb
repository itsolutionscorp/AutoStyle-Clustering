class ETL

  def self.transform(score_sets)
    scores = ScrabbleScores.new

    score_sets.each do |score, letters|
      scores[letters] = score
    end

    scores.to_h
  end
end

class ScrabbleScores

  def initialize
    @scores = {}
  end

  def []=(letters, score)
    letters.each do |letter|
      @scores[letter.downcase] = score
    end
  end

  def to_h
    @scores.dup
  end
end
