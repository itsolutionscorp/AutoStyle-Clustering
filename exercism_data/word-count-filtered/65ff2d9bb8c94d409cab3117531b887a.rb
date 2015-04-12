class Phrase
  def initialize(phrase)
  	@phrase = phrase.downcase.gsub(/[^a-z0-9'\s]/, ' ').split(' ')
  end

  def word_count
  	words = {}
  	@phrase.map { |word| 
  		          if words[word]
  		            words[word] += 1
  		          else
  		            words[word] = 1
  		          end  }
  	words
  end
end
