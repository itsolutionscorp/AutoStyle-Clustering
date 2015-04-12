class Phrase

  attr_reader :word_count

  def initialize(words)
    @word_count = Hash.new(0)
    words.downcase.scan(/[[:alnum:]]+/) { |w| 
    	@word_count[w] += 1 
    }
  end

end
