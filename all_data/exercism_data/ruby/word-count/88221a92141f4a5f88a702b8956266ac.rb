class Phrase

  def initialize(input)
    @sentence = input
  end

  def word_count
    appearences = Hash.new(0)
    input_without_punctuation.downcase.split.each { |word| appearences[word] += 1 }
    appearences
  end

  private

  def input_without_punctuation
    @sentence.gsub(/[^a-zA-Z1-9\s]/, " ")
  end

end
