class Phrase
  attr_reader :text

  def initialize(text)
    @text = text
  end

  def word_count
    all_words.each_with_object({}) do |word, distinct_words|
      if distinct_words.has_key? word
        distinct_words[word] += 1
      else
        distinct_words[word] = 1
      end
    end
  end

  def all_words
    text.downcase.scan /\w+/
  end

end
