class Phrase

  def initialize sentence
    @sentence = sentence
  end

  def word_count
    initial_value = Hash.new {|h, k| h[k] = 0}
    @sentence.downcase.scan(/[\w']+/).each_with_object(initial_value) do  |word, words_frequency|
      words_frequency[word] += 1
    end
  end
end
