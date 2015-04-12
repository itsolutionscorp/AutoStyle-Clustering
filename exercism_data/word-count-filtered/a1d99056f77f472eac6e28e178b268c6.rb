class Phrase
  def initialize(string)
    @words = string.downcase.scan(/\w+/)
  end

  def word_count
    @words.inject(Hash.new(0)) do |results, word|
      results[word] += 1
      results
    end
  end
end
