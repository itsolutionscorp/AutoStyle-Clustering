class Phrase

  def initialize(phrase)
    @phrase = phrase.downcase
  end

  def word_count
    counts = Hash.new(0)
    @phrase.scan(/\w+/) { |word| counts[word] += 1 }
    counts
  end
 
end
