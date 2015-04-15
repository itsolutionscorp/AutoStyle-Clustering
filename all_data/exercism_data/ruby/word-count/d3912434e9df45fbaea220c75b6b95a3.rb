class Words
  def initialize phrase
    @word_list = phrase.downcase.split /\W+/
  end

  def count
    @word_list.each_with_object(Hash.new(0)) { |word, result|
      result[word] += 1
    }
  end
end
