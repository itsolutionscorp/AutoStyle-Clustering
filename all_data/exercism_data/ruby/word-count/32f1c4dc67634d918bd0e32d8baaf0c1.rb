class Phrase
  def initialize(sentence)
    @words = sentence.words
  end

  def word_count
    pairs = Hash.new(0)
    @words.each_with_object(pairs){ |w, pairs|
      pairs[w.downcase] += 1    #we don't want to differ "first" and "First"
   }
  end

end


class String
  def words
    scan(/[\w|-|']+/) # we want "don't" and "one-way" treated as one word
  end
end
