class Phrase
  attr_reader :word_count
  def initialize(words)
    @word_count = {}
    @words = count words
  end

  def count words
    words = words.downcase.gsub(/[^a-z0-9'\s]/i,' ').split
    words.uniq.each do |word|
      count = words.count(word)
      @word_count[word] = count
    end
  end
end
