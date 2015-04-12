class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = @phrase.downcase.split(/[\W]+/)
    result = words.each_with_object({}) do |word, res|
      res[word] = words.count(word) if !res.has_key?(word)
    end
  end
end
