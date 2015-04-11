class Phrase
  attr_reader :text
  
  def initialize(text)
    @text = text
  end

  def word_count
    words_used.each_with_object({}) do |word, counts|  
      counts[word] = tokenized_words.count(word)
    end
  end

private

  def tokenized_words
    @tokenized_words ||= @text.downcase.split(/\W+/)
  end

  def words_used
    tokenized_words.uniq
  end

end
