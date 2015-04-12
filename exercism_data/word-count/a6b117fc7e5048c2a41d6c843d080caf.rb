class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @count ||= words.each_with_object(hash) { |word, count| count[word] += 1 }
  end

  private
  attr_reader :phrase

  def words
    downcased_phrase.split(%r{\W+})
  end

  def downcased_phrase
    sanitised_phrase.downcase
  end

  def sanitised_phrase
    phrase.delete(':!&@%^$')
  end

  def hash
    Hash.new { |key, value| key[value] = 0 }
  end
end
