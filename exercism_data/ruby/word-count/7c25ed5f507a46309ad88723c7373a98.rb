class Phrase

  NON_WORD_CHARS = /[^a-z0-9']+/

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    parse_into_words.each_with_object({}) do |word, counts|
      counts[word] = counts[word].to_i + 1
    end
  end

  def parse_into_words
    @phrase.downcase.gsub(NON_WORD_CHARS, ' ').split
  end

end
