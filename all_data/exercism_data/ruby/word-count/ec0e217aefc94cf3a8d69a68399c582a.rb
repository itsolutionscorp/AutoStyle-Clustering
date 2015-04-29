class Phrase
  def initialize(phrase)
    @final_word_array = phrase.downcase.scan(/[\w']+/)
  end

  def word_count
    hash = Hash.new(0)
    @final_word_array.each { |word|
      hash[word] += 1 }
    hash
  end
end
