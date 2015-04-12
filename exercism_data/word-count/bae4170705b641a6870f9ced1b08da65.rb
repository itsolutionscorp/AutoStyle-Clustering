class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = {}
    phrase.split(%r/[,\s]+/).each do |word|
      clean_word = CleanWord.new(word)
      next if clean_word.blank?
      if words["#{clean_word}"].nil?
        words["#{clean_word}"] = 1
      else
        words["#{clean_word}"] += 1
      end
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
