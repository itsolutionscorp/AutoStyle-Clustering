class Phrase

  attr_accessor :words

  def initialize(phrase)
    @words = phrase.gsub(/[^(\w|\s)]/," ").downcase.split
  end

  def word_count
    unique_words = Hash.new

    self.words.uniq.each do |word|
      add_word_to_word_list(word,unique_words)
    end
    unique_words

  end

  def add_word_to_word_list(word,list)
    (list).merge!({word => self.words.count(word)})
  end
end
