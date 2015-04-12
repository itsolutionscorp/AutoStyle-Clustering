class Phrase
  def initialize(phrase)
    @words = phrase.downcase.split(/\W+/)
  end

  def word_count
    @words.uniq.each_with_object({}) do |word, counted_words|
      counted_words[word] = @words.count(word)
    end
  end
end
