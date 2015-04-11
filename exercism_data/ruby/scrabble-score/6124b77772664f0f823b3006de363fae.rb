class Scrabble
  attr_reader :word
  MAP_SCORE = { [ 'A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T' ] => 1,
                [ 'D', 'G' ] =>  2,
                [ 'B', 'C', 'M' , 'P' ] => 3,
                [ 'F', 'H', 'V', 'W', 'Y' ] => 4,
                [ 'K' ] => 5,
                [ 'J', 'X'] => 8,
                [ 'Q', 'Z'] => 10 }

  def initialize word
    @word = !word ? "" : normalize_word(word)
  end
  
  def normalize_word word
    word.scan(/[aA-zZ]+/).join.upcase
  end
  
  def score
    get_word_count.inject(0) do |sum, (char, count)|
      sum += count * get_score(char)
    end
  end
  
  def get_score char
    score = MAP_SCORE.select{ |key, value| key.include?(char) }.values.last
  end
  
  def get_word_count
    @word.chars.uniq.each_with_object({}){ |item, hash| hash[item] = word.count item }
  end
  
  def self.score word
    new(word).score
  end
end
