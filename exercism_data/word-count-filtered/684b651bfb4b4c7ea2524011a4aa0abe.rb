class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    word_count_hash = Hash.new(0)
    @phrase.scan(/[a-zA-Z0-9]+/){|word| 
      word_count_hash[word.downcase] += 1
    }
    word_count_hash
  end

end
