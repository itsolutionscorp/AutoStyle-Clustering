class Phrase
  attr_reader :word_count

  def initialize(str)
    words = make_word_array(str)
    @word_count = make_count_hash(words)
  end

  private

  def make_word_array(str)
    str.downcase.scan(/\w+/)
  end

  def make_count_hash(words)
    words.each_with_object(Hash.new(0)) { |word, hash| hash[word] += 1 }
  end
end
