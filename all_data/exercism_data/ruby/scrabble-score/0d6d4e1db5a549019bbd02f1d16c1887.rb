class Scrabble
  SCORES = {
    ['A','E','I','O','U','L','N','R','S','T']=>1,
    ['D','G']=>2,
    ['B','C','M','P']=>3,
    ['F','H','V','W','Y']=>4,
    ['K']=>5,
    ['J','X']=>8,
    ['Q','Z']=>10,
    [' ', "\n", "\t"] => 0
  }

  class << self
    def transform(scrabble_letter_score_data)
      scrabble_letter_score_data.each_with_object({}) do |(letters, score), result|
        letters.each{|letter| result[letter.downcase] = score}
      end
    end

    def score(word)
      self.new(word).score
    end
  end

  def initialize(word)
    @word = word || ""
  end

  def alphabet_score(alphabet)
    @optimized_score_index ||= self.class.transform(SCORES)
    @optimized_score_index[alphabet.downcase]
  end

  def score
    @word.chars.inject(0){|sum, alphabet| sum + alphabet_score(alphabet) }
  end
end
