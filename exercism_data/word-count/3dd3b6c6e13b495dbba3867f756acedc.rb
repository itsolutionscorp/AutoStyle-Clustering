class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = Hash.new(0)
    phrase.split(%r/[,\s]+/).each do |word|
      clean_word = CleanWord.new(word)
      next if clean_word.blank?
      words[clean_word.to_s] += 1
    end

    return words
  end
end

class CleanWord
  def initialize(word)
    @word = word.downcase.gsub(/\W*/, '')
  end

  def blank?
    @word.nil? or @word.eql?('')
  end

  def to_s
    @word
  end
end
