class Phrase
  def initialize( phrase )
    @phrase = phrase
  end

  def word_count
    @word_count ||= count words_in_phrase
  end

  private

  def words_in_phrase
    @phrase.downcase.scan /[a-z0-9']+/
  end

  def count( words )
    words.reduce(Hash.new(0)) do |word_count, word|
      word_count[word] += 1
      word_count
    end
  end
end
