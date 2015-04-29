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

  def normalized_word_list
    words.split.map {|word| word.gsub(/\W/, '').downcase }.reject {|word| word.empty? }
  end
end
