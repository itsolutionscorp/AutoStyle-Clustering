class Phrase
  def initialize(words)
    @words = prepare(words)
  end
  
  def word_count
    hash = Hash.new(0)
    @words.each { |word| hash[word.downcase] += 1 }
    hash
  end

  private 

    def prepare(words)
      words.
        gsub(/[:!&@$%^&.]/,'').
        gsub(/[,]/,' ').
        split
    end
  
end
