class Phrase

  def initialize(phrase)
    @phrase = phrase.to_s
  end

  def word_count
    phrase_array = @phrase.downcase.scan(/\w+/)
    phrase_array.inject(Hash.new(0)) do |hash, word|
      hash[word] += 1
      hash
    end
  end

end
