class Phrase
  def initialize(phrase)
    @words = phrase.gsub(/[^\w\s]/, ' ').downcase.split
  end

  def word_count
    @words.each_with_object({}) do |word, result|
      count = result[word] || 0
      result[word] = count + 1
    end
  end
end
