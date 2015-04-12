class Phrase
  def initialize(phrase)
    @all_words = phrase.downcase.split(/\W+/)
  end
  def word_count
    word_count = {}
    @all_words.uniq.each do |word|
      word_count.store(word, @all_words.count(word))
    end
    word_count
  end
end
