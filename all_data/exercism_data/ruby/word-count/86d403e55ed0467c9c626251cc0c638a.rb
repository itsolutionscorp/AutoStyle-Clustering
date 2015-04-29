class Phrase

  def initialize(phrase)
    @phrase = phrase
    format_input
    count_words
  end

  def word_count
    @count
  end

  private

  def format_input
    @phrase.downcase!
    @phrase.gsub!(/[^[[a-z]|[ ]|[0-9]]]/, '')
  end

  def count_words
    words = @phrase.split(' ')
    @count = {}

    words.each do |word|
      if @count[word]
        @count[word] += 1
      else
        @count[word] = 1
      end
    end
  end

end
