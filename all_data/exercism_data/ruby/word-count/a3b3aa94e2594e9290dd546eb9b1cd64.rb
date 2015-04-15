class Phrase
  def initialize(words)
    @words = remove_bad_input(words)
  end

  def remove_bad_input(words)
    words.downcase.scan(/\w+/)
  end

  def word_count
    @words.each_with_object(Hash.new(0)) { |word, word_hash| word_hash[word] += 1 }
  end
end
