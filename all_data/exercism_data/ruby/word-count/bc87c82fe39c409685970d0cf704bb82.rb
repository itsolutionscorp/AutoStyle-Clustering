class Phrase
  def initialize(phrase)
    @words = find_words(phrase)
  end

  def word_count
    count_words unless defined?(@count)
    count
  end

  def count_words
    @words.each do |word|
      record_word(word)
    end
  end

  def record_word(word)
    count[word] += 1
  end

  def count
    @count ||= Hash.new(0)
  end

  def find_words(phrase)
    # Strip down to just words and lower case
    phrase.scan(/\w+/).map(&:downcase)
  end
end
