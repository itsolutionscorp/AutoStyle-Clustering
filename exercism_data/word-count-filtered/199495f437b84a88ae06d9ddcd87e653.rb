class Phrase
  def initialize(words)
    @words = words
  end

  def word_count
    count = Hash.new(0)
    @words.downcase.scan(/\w+/m) { |w| count[w] += 1 }
    count
  end
end
