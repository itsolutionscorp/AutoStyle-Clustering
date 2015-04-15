class Phrase
  attr_reader :text

  def initialize(text)
    @text = text
  end

  def word_count
    all_words = clean_text.split
    distinct_words = {}

    all_words.each do |word|
      if distinct_words.has_key? word
        distinct_words[word] += 1
      else
        distinct_words[word] = 1
      end
    end
    distinct_words
  end

  def clean_text
    cleaned_text = text.downcase
    cleaned_text.gsub /[^\w]/, ' '
  end

end
