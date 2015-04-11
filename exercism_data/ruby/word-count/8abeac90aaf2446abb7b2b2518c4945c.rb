class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    total_occurences = Hash.new(0)

    each_lowercase_word @phrase do |word|
      total_occurences[word] += 1
    end
    total_occurences
  end

  private
  def each_lowercase_word(string, &block)
    string.downcase.scan(/[a-zA-Z0-9]+/, &block)
  end
end
