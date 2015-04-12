class Phrase
  def initialize(text)
    @text = text || ""
  end

  def word_count
    results = Hash.new(0)
    words_in_string(text) do |word|
      results[word] += 1
    end
    results
  end

private
  attr_reader :text

  def words_in_string(string, &block)
    string.downcase.scan(/\w+/, &block)
  end
end
