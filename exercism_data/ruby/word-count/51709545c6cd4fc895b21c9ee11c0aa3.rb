class Phrase
  attr_reader :text

  def initialize(text)
    @text = String(text)
  end

  def word_count
    word_count_hash    
  end

private

  def word_count_hash
    Hash[word_count_array]
  end

  def word_count_array
    words.map do |word|
      [word, words.count(word)]
    end
  end

  def words
    @words ||= self.text.downcase
             .scan(/\w+/)
  end
end
