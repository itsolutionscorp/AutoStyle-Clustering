class Phrase
  private
  INCLUDE_CHARACTERS_REGEX=/['\w]+/
  attr_reader :sentence, :words

  def initialize(sentence)
    @words=Hash.new(0)
    @sentence=sentence
  end

  def split_sentence
    sentence.downcase.scan(INCLUDE_CHARACTERS_REGEX).each { |word| words[word]+=1}
  end

  public
  def word_count
    split_sentence if words.empty?
    words
  end
end
