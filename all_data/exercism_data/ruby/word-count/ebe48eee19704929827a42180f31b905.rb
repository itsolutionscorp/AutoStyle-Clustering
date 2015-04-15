class Phrase
  def initialize(words)
    @words = words
  end

  def word_count
    Phrase.count_words(Phrase.clean(@words))
  end

  def self.clean(word_string)
    word_string.scan(/\w+/).map(&:downcase)
  end

  def self.count_words(word_list)
    word_list.each_with_object(Hash.new(0)) do |word, results|
      results[word] += 1
    end
  end
end
