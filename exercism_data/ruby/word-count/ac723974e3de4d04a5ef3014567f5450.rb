class Phrase

  def initialize text
    @text = text
  end

  def word_count
    words_counter = WordsCounter.new @text
    words_counter.get_count
  end

end

class WordsCounter

  def initialize text
    @text = text
  end

  def get_count
    return @count_hash if @count_hash

    init_counter
    normalize
    tokenize
    count_tokens
  end

  private

  def init_counter
    @count_hash = Hash.new(0)
  end

  def normalize
    @text.downcase!
  end

  def tokenize
    @tokens = @text.scan(/\w+/)
  end

  def count_tokens
    @tokens.each { |word| @count_hash[word] += 1 }
    @count_hash
  end

end
