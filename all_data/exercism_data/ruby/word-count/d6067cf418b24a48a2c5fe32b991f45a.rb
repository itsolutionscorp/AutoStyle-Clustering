class Phrase
  attr_accessor :phrase, :word_count_hash

  def initialize(phrase)
    @phrase = phrase
    @word_count_hash = {}
  end

  def word_count
    phrase_array = phrase.split(/[ ,:!@#\$%\^&*]/)
    phrase_array = phrase_array.map(&:downcase)
    phrase_array.uniq.each do |word|
      next if word.empty?
      word_count_hash[word.downcase] = phrase_array.count(word)
    end
    word_count_hash
  end

end
