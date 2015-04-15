class Phrase
  attr_reader :text

  def initialize(text)
    @text = text
  end

  def word_count
    words.inject(Hash.new(0)) do |hash,word|
      hash[word.downcase] += 1
      hash
    end
  end

  #######
  private
  #######

  def words
    text_without_puncuation.split(/\s+/)
  end

  def text_without_puncuation
    text.gsub(/[^0-9a-z\s]/i,' ')
  end
end
