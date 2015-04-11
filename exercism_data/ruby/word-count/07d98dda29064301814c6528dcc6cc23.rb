class Phrase

  def initialize(word_string)
    @raw_words = word_string
  end

  def words
    @words ||= process
  end

  def word_count
    histogram(words)
  end

  private
  def histogram inp; hash = Hash.new(0); inp.each {|k,v| hash[k]+=1}; hash; end

  def process
    split_into_words.map{|dirty_word| clean_word(dirty_word) }.reject{|w| w.nil? || w.empty? }
  end

  def split_into_words
    @raw_words.gsub(/,/, ' ').split
  end

  def clean_word(word)
    word.downcase.gsub(/[^'\w\d]/,'')
  end
end
