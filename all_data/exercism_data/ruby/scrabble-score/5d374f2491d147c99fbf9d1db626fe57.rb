class Scrabble

  def initialize(word)
    @word = word
  end

  def score
    self.class.score(@word)
  end

  def self.score(word)
    unless word.nil?
      score = word.strip.each_char.map {|c| find_letter_score(c) }.reduce(:+)
    end
    score ||= 0
  end

  def self.find_letter_score(character)
    score_guide = [
      [%w(a e i o u l n r s t), 1],
      [%w(d g), 2],
      [%w(b c m p), 3],
      [%w(f h v w y), 4],
      [%w(k), 5],
      [%w(j x), 8],
      [%w(q z), 10]
    ]
    score_guide.each do |range|
      if range.first.include? character.downcase
        @value = range.last
      end
    end
    @value ||= 0
  end
end
