class Phrase
  attr_reader :word_count

  def initialize(phrase)
    @words = phrase.scan(/[\w']+/)
    @word_count = {}
    @words.each { |word| add_word(word.downcase) }
  end

  private
    def add_word(word)
      if @word_count.has_key?(word)
        @word_count[word] += 1
      else
        @word_count.store(word, 1)
      end
    end
end
