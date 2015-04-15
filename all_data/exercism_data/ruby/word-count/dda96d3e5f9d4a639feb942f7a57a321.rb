class Words
  attr_accessor :words

  def initialize(words)
    self.words = words
  end

  def count
    normalized_word_list.each_with_object Hash.new(0) do |word, result|
      result[word] += 1
    end
  end

  private

  def normalized_word_list
    words.downcase.split(/\W+|\s+/)
  end
end
