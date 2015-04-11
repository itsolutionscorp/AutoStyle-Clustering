class Phrase
  attr_reader :phrase, :word_count

  def initialize(phrase)
    @phrase = phrase
    @word_count = Hash.new(0)
  end

  def word_count
    return @word_count unless @word_count.empty?
    get_words.each do |word|
      @word_count[word] += 1
    end
    @word_count
  end

  private
    def get_words
      @phrase.downcase.scan(/[\w']+/)
    end
end
