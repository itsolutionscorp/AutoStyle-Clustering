class Phrase

  def initialize(phrase)
    @phrase = phrase.to_s
  end

  def phrase_array
    @phrase.downcase.scan(/\w+/)
  end

  def word_count
    phrase_array.each_with_object(Hash.new(0)) do |word, hash|
      hash[word] += 1
    end
  end

end
