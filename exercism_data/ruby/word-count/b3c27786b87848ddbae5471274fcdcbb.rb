class Phrase
  def initialize(words)
    @words = words.downcase.scan(/\w+/)
  end

  def word_count
    word_hash = Hash.new(0)
    @words.each { |word| word_hash[word] += 1 }
    word_hash
  end
end
