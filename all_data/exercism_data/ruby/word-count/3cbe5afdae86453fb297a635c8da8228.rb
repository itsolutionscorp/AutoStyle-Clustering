class Words
  def initialize phrase
    @word_list = phrase.split /\W+/
  end

  def count
    @word_list.each_with_object(Hash.new(0)) { |word, result|
      result[word.downcase] += 1
    }
  end
end
