class Phrase
  def initialize(text)
    @text = text
  end

  def word_count
    @word_count ||= calculate_word_count 
  end

  private

  def calculate_word_count
    @text.scan(/\w+/).each_with_object(Hash.new(0)) do |word, word_count|
      word_count[word.downcase] += 1 
    end
  end
end
