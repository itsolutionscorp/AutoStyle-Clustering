class Phrase

  def initialize(str)
    @str = str
  end

  def word_count
    words = make_word_array(@str)
    make_count_hash(words)
  end

  private

  def make_word_array(str)
    str.downcase.scan(/\w+/)
  end

  def make_count_hash(words)
    words.each_with_object(Hash.new(0)) { |word, hash| hash[word] += 1 }
  end
end
