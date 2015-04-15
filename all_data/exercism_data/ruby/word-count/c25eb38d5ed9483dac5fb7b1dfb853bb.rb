class Phrase
  def initialize(text)
    @text = text
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, counts|
      counts[word.downcase] += 1
    end
  end

  private
  attr_reader :text

  def words
    text_without_punctuation.split
  end
  
  def text_without_punctuation
    not_alphanumeric_or_whitespace = /[^[:alnum:]|[:space:]]/
    text.gsub(not_alphanumeric_or_whitespace, " ")
  end
end
