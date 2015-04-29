class Phrase
  attr_reader :word_count
  def initialize(phrase)
    @phrase = phrase.downcase
    @word_count = Hash.new
    to_hash
  end

  def to_hash
    @phrase.split.each do |word|
      word = clean!(word)
      @word_count.key?(word) ? (@word_count[word] += 1) : (@word_count[word] = 1)
    end
  end

  def clean!(word)
    until (word[-1] =~ /^[0-9a-z ]/i) == 0
      word.delete!(word[-1])
    end
    word
  end
end

# word = Phrase.new("Some, phrase!!! Where, something is cool!")
# p word.word_count
