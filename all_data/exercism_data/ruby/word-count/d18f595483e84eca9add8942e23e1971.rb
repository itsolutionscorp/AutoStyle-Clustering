class Phrase
  def initialize(text)
    @word_count = Hash.new(0)
    text.split(/[^'\w]+/).each { |word|
      @word_count[word.downcase] += 1
    }
  end

  attr_reader :word_count

end
