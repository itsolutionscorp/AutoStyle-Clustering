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
    words.reduce(Hash.new(0)) do |hash, word|
      hash[word] += 1
      hash
    end
  end
end
