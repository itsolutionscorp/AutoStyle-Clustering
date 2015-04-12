class Phrase
  attr_accessor :phrase, :word_count_hash

  def initialize(phrase)
    @phrase = phrase.split(/[ ,:!@#\$%\^&*]/).map(&:downcase)
    @word_count_hash = {}
  end

  def word_count
    phrase.uniq.each do |word|
      next if word.empty?
      word_count_hash[word.downcase] = phrase.count(word)
    end
    word_count_hash
  end
end
