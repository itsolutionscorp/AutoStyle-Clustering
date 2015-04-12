class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @phrase.downcase.scan(/[a-zA-Z0-9']+/).each_with_object({}) do |word, hash|
      if !hash.key?(word)
        hash.merge!(word => 1)
      else
        hash[word] += 1
        hash
      end
    end
  end
end
