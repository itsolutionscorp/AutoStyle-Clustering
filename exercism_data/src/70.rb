class Phrase

  def initialize sentence
    @sentence = sentence
  end

  def word_count
    initial_value = Hash.new {|h, k| h[k] = 0}
    @sentence.downcase.scan(/[\w']+/).reduce initial_value do |words_frequency, word|
      words_frequency[word] += 1
      words_frequency
    end
  end
end
