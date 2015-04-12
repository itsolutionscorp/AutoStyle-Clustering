class Phrase
  attr_reader :sentence
  def initialize sentence
    @sentence = sentence
  end

  def word_count
    sentence
      .scan(/\w+/)
      .each_with_object(Hash.new) do |word, counts|
        word.downcase!
        counts[word] = counts.fetch(word, 0) + 1
      end
  end
end
