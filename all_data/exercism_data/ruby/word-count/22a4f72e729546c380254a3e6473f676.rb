class Phrase
  def initialize(words)
    @words = words.downcase
  end

  def word_count
    each_word_with_object(Hash.new(0)) {|word, hash| hash[word] += 1}
  end

  private
  def each_word_with_object(object)
    each_word.each_with_object(object) do |word, hash|
      yield word, hash
    end
  end

  def each_word
    @words.scan(/\w+/)
  end
end
