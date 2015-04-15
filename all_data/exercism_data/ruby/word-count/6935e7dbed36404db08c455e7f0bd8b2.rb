class Phrase
  def initialize(text)
    @text = text
  end

  def text_without_punctuation
    @text.gsub(/[^A-Za-z0-9 ]/, " ")
  end

  def sanitized_text
    text_without_punctuation.downcase
  end

  def word_count
    word_count = {}

    words = sanitized_text.split(/\W+/)

    words.each do |word|
      if word_count.include?(word)
        word_count[word] += 1
      else
        word_count[word] = 1
      end
    end
    word_count
  end
end
