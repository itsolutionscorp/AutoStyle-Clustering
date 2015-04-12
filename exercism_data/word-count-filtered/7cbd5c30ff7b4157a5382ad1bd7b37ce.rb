class Phrase

  def initialize(phrase)
    @words_array = phrase.downcase.scan(/\w+/)
  end
  
  def word_count
    @words_array.each_with_object(Hash.new(0)) { 
      |word, counts_hash| counts_hash[word] += 1 
    }
  end

end
