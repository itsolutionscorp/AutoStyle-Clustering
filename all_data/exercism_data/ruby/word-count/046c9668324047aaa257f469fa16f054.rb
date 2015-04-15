class Phrase
  def initialize str
    @words = str.downcase.scan(/\w+\'*\w*/)
  end
  def word_count
    Hash[@words.uniq.collect { |uniq_word| [uniq_word, @words.select{|word| word==uniq_word}.size] }]
  end
end
