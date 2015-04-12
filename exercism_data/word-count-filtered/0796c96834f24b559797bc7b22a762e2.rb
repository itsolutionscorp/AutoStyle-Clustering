class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = @phrase.downcase.split(/[\W]+/)
    words.each_with_object(Hash.new) do |word, result|
      result[word] = words.count(word) if !result.has_key?(word)
    end
  end
end
