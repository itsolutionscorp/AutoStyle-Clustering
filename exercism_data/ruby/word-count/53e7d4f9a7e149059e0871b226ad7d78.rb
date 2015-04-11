class Phrase
  attr_reader :sentence

  def initialize(sentence)
    @sentence  = sentence
  end

  def word_count
    word.scan(/\w+/).each_with_object(Hash.new(0)) {|word, hash| hash[word]+=1 }
  end
 
  private

  def word
    sentence.downcase
  end
end
