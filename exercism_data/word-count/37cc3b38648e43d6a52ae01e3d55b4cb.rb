class Phrase

  def initialize(content)
    @lowercase_words = content.downcase.split(/\W+/)
  end

  def word_count
    words_with_count = Hash.new(0)
    @lowercase_words.each { |word| words_with_count[word] += 1 }
    words_with_count
  end
end
