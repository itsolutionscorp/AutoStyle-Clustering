class Phrase
  def initialize phrase
    @phrase = downcase_and_strip_punctuation! phrase
  end

  def word_count
    words = {}
    uniq_words.each do |word|
      words[word] = count_for word
    end

    words
  end

  private

  def split_phrase
    @split_phrase ||= @phrase.split
  end

  def uniq_words
    split_phrase.uniq
  end

  def count_for word
    split_phrase.count word
  end

  def downcase_and_strip_punctuation! phrase
    phrase.downcase.gsub /[^\s\w]/, ' '
  end
end
