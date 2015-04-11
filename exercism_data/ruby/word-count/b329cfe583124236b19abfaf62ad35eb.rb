class Phrase
  def initialize(phrase)
    @words = phrase_to_array(phrase)
  end

  def word_count
    @words.each_with_object(Hash.new(0)){ |word, hash| hash[word] += 1 }
  end

  private
  
  def phrase_to_array(phrase)
    phrase.downcase.split(/\W+/)
  end
end
