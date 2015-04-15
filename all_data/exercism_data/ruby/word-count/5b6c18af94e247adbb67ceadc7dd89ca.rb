class Phrase
  attr_reader :text

  def initialize(text)
    @text = text
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word,hash|
      hash[word.downcase] += 1
    end
  end

  #######
  private
  #######

  def words
    text_without_punctuation.split(/\s+/)
  end

  def text_without_punctuation
    text.gsub(/[^0-9a-z\s]/i,' ')
  end
end
