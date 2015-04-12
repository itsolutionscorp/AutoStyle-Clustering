class Phrase
  NON_WORD_CHARS = /\W+/ # http://rubular.com/r/JTLcQb1bvE

  def initialize(phrase)
    @word_count = Hash.new(0)

    phrase.split(NON_WORD_CHARS).each do |word|
      @word_count[word.downcase] += 1
    end
  end

  def word_count
    @word_count
  end
end
