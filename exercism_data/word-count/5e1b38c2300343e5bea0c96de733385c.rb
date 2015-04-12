class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    return @word_count if @word_count
    @word_count = Hash.new { |h,k| h[k] = 0 }
    words.each do |word|
      @word_count[word] += 1
    end
    @word_count
  end

  private

  attr_reader :phrase

  def words
    normalize_case
    _phrase = phrase.gsub("\s", ",")
    _words = _phrase.split(",")
    strip_punctuation(_words)
  end

  def normalize_case
    phrase.downcase!
  end

  def strip_punctuation(words)
    _words = words.map do |word|
      word.gsub(/[^\w|\s]+/, "")
    end
    _words.compact.reject(&:empty?)
  end

end
