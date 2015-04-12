class Phrase
  attr_reader :phrase

  def initialize phrase
    @phrase = phrase
    duplicates = word_count
    p duplicates
  end

  def word_count 
    words = splits
    count_duplicate words
  end

  private

  def splits 
    words = @phrase.split(/[\s,]+/) 
  end

  def count_duplicate words
    duplicates = Hash.new(0)
    clean_words = clean_words(words)
    clean_words.select {|word| word.match(/\w/)}.map {|word| duplicates[word.downcase] += 1 }
    duplicates
  end

  def clean_words words
    words.map{|word| word.gsub(/\W/,'')}
  end

end
