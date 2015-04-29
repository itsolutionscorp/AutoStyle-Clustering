class Phrase

  def initialize(string)
    @phrase = string
  end

  def word_count
    @phrase.downcase.scan(/[\w']+/).inject(Hash.new(0)) do 
      |counts,word| counts[word] += 1
      counts
    end
  end

end
