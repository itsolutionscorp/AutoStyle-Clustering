class Phrase
  def initialize word
    @word = word
  end

  def word_count
    list_of_valid_words.each_with_object(Hash.new(0)) do |word, hash|
      hash[word] += 1
    end
  end

  def list_of_valid_words
    @word.downcase.split(/[\W]/).select do |word|
      word unless  word.empty?
    end
  end

end
