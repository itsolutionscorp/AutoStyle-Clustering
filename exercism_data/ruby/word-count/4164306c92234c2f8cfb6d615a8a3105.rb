class Phrase
  def initialize(phrase)
    @final_word_array = phrase.downcase.scan(/[\w']+/)
  end

  def word_count
    @final_word_array.each_with_object(Hash.new(0)) { |word, hash|
      hash[word] = @final_word_array.count(word) }
    end
end
