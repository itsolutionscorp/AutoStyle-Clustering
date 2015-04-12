class Phrase
  def initialize(phrase)
    @phrase = phrase.downcase
  end

  def word_count
    word_count_hash = Hash.new(0)
    @phrase.scan(/\w+/){|word| 
      word_count_hash[word] += 1
    }
    word_count_hash
  end

end
