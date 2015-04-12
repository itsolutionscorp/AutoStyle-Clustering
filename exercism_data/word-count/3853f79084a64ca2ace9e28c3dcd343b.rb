class Phrase
  attr_reader :word_count

  def initialize(text)
    @word_count = Hash.new(0)
    text.downcase.split(/[^a-z0-9']+/).each do |word|
      @word_count[word] += 1
    end
  end
end