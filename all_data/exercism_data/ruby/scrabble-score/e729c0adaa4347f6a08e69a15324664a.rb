class Scrabble

  LETTER_SCORE_HASH = Hash[*(
    %w{A E I O U L N R S T}.map{|e| [e, 1]} +
    %w{D G}.map{|e| [e, 2]} +
    %w{B C M P}.map{|e| [e, 3]} +
    %w{F H V W Y}.map{|e| [e, 4]} +
    ['K', 5] +
    %w{J X}.map{|e| [e, 8]} +
    %w{Q Z}.map{|e| [e, 10]}
  ).flatten]

  def self.score(word, options = {})
    self.new(word, options).score
  end

  def initialize(word, options = {})
    @word = word.to_s.upcase.gsub(/\s*/, '')
    @doubled_letters = Array(options[:double_letter]).map(&:upcase)
    @tripled_letters = Array(options[:triple_letter]).map(&:upcase)
    @doubled_word = options[:double_word]
    @tripled_word = options[:triple_word]
  end

  def score
    score = @word.each_char.inject(0) do |sum, letter|
      sum + letter_score(letter)
    end
    word_score(score)
  end

  private
  def letter_score(letter)
    score = LETTER_SCORE_HASH[letter]
    if @doubled_letters[0] == letter
      @doubled_letters.shift
      score = 2*score
    elsif @tripled_letters[0] == letter
      @tripled_letters.shift
      score = 3 * score
    end
    score
  end

  def word_score(score)
    if @doubled_word
      score = 2*score
    elsif @tripled_word
      score = 3*score
    end
    score
  end

end
