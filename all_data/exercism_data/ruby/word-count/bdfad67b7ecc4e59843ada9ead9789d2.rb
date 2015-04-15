class Phrase

  def initialize(words)
    @words = words.downcase.scan(/[\w']+/)
  end

  def word_count
    @words.each_with_object(Hash.new(0)) do |word, word_count|
      word_count[word] += 1
    end
  end

end
