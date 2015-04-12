class Phrase
  def initialize word
    @word = word
  end

  def word_count
    @word.downcase.split(/\W+/).each_with_object(Hash.new(0)) { |word, hash| hash[word] += 1 }
  end

end
