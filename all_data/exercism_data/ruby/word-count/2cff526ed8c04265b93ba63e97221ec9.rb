class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = Hash.new(0)
    clean_words.each do |clean_word|
      words[clean_word.to_s] += 1
    end

    return words
  end

  private

  def clean_words
    words = phrase.split(%r/[,\s]+/).collect { |word| word.downcase.gsub(/\W*/, '') }
    words.reject { |word| blank?(word) }
  end
    
  def blank?(word)
    word.nil? or word.eql?('')
  end
end
