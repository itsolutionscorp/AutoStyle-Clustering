class Phrase
  def initialize(sentence)
    @sentence = sentence
  end

  def word_count
    @counts ||= calculate_word_count
  end

  def calculate_word_count
    @counts = Hash.new(0)
    @sentence.downcase.split(/[^\w\']+/).each do |word|
      @counts[word.downcase] += 1
    end
    @counts
  end
end
