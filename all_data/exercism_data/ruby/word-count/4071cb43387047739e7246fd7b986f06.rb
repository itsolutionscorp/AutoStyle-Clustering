class Phrase
  def initialize(phrase = nil)
    @phrase = phrase.to_s
  end
  
  def word_count
    @phrase.strip.downcase.split(/\W+/).each_with_object(Hash.new(0)) do
      |word, hash| hash[word] += 1
    end 
  end
end
