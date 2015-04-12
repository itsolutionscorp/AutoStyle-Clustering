class Phrase

  def initialize(input)
    @words_array = input.downcase.scan(/\w+/)
  end
  
  def word_count
    @words_array.each_with_object(Hash.new(0)) { |word, counts_hash| counts_hash[word] += 1 }
  end

end
