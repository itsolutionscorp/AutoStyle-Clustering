class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    word_count = Hash.new
    @phrase.downcase.scan(/[[:alnum:]]+/).each do | word | 
      word_count.key?(word) ? word_count[word] += 1 : word_count[word] = 1
    end
    return word_count
  end

end
