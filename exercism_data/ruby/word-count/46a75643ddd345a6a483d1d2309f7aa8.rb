class Words
  attr_accessor :words

  def initialize(words)
    self.words = words
  end

  def count
    result = Hash.new(0)
    normalized_word_list.inject result do |result, word|
      result[word] = result[word].succ
      result
    end
  end

  private

  def word_list
    words.split
  end

  def normalized_word_list
    word_list.map {|word| word.gsub(/\W/, '').downcase }.reject {|word| word.empty? }
  end
end
