class Scrabble
  def initialize(word_to_score)
    @word_to_score = word_to_score
    prepare_scoring
  end

  def score
    score = 0
    unless @word_to_score == nil
      @word_to_score.downcase!
      @word_to_score.scan(/./) do |letter|
        score += score_letter(letter)
      end
    end
    score
  end

  def self.score(word_to_score)
    Scrabble.new(word_to_score).score
  end

  private

  def score_letter(letter)
    @score_map[letter].to_i
  end

  def prepare_scoring
    score_map = Hash.new

    ['a', 'e', 'i', 'o', 'u', 'l', 'n', 'r', 's', 't'].each do |key|
      score_map[key] = 1
    end
    ['b', 'c', 'm', 'p'].each do |key|
      score_map[key] = 3
    end
    ['f', 'h', 'v', 'w', 'y'].each do |key|
      score_map[key] = 4
    end
    score_map['k'] = 5
    
    ['q', 'z'].each do |key|
      score_map[key] = 10
    end

    @score_map = score_map
  end
end
