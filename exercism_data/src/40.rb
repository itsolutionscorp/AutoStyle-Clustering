class Phrase
  def initialize(words)
    @words = words.gsub(/[^\w\s]/, ' ').downcase
  end

  def word_count
    @words.split.each_with_object({}) do |word, result|
      count = result[word] || 0
      result[word] = count + 1
    end
  end
end
