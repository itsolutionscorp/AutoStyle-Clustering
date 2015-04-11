class Phrase
  attr_reader :text

  NON_WORD_CHARS = /[^\d\w]+/

  def initialize(text)
    @text = String(text)
  end

  def word_count
    word_count_hash    
  end

  def word_count_hash
    Hash[word_count_array]
  end

  def word_count_array
    words.map do |word|
      [word, words.count(word)]
    end
  end

  def words
    self.text.gsub(NON_WORD_CHARS,' ')
             .squeeze(' ')
             .downcase
             .split(' ')
  end
end
