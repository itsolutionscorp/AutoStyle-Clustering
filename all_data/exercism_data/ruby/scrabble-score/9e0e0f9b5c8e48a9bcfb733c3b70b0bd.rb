class Scrabble
  SCORES = [ 
    [1,  'A E I O U L N R S T'],
    [2,  'D G'],
    [3,  'B C M P'],
    [4,  'F H V W Y'],
    [5,  'K'],
    [8,  'J X'],
    [10, 'Q Z']]

  def initialize(word)
    @word = word.to_s.upcase
    initialize_letter_scores
  end
  
  def self.score(word)
    new(word).score
  end

  def score
    @word.chars
      .map { |char| @letter_scores[char] }
      .reduce(0) { |sum, score| sum + score }
  end

  def initialize_letter_scores
    @letter_scores = Hash.new(0)
    SCORES.each { |score, letters| assign_score(letters, score) }
  end

  def assign_score(letters, score)
    letters.split.each { |letter| @letter_scores[letter] = score }
  end
end
