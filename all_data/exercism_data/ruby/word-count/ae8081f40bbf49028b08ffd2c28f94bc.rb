class Words
  attr_reader :words
  
  def initialize(sentence)
    @words = prepare(sentence)
  end

  def count
    words.each_with_object({}){|word, h|h[word]=words.count(word)}
  end
  
  private

  def prepare(sentence)
    sentence.gsub(/[^0-9A-Za-z]/, ' ').downcase.split(" ")
  end
end
