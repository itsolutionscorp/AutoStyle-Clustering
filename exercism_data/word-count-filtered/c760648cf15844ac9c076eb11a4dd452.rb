class Phrase
  WORD = /\w+/
  CONTRACTION = /#{WORD}'#{WORD}/

  def initialize(string)
    @words = string.downcase.scan(/#{CONTRACTION}|#{WORD}/)
  end

  def word_count
    @words.each.with_object(Hash.new(0)) do |word, counts_hash|
      counts_hash[word] += 1
    end
  end
end
