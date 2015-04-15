class Words
  attr_accessor :words

  def initialize(words)
    self.words = words
  end

  def count
    normalized_word_list.each_with_object Hash.new(0) do |word, result|
      result[word] = result[word].succ
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
