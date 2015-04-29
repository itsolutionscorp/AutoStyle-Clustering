class Phrase
  attr_reader :sentence
  def initialize sentence
    @sentence = sentence
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, counts|
      word.downcase!
      counts[word] = counts[word] + 1
    end
  end

  def words
    sentence.scan(/\w+/)
  end
end
