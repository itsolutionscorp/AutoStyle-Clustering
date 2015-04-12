class Words
  attr_reader :words_list

  def initialize(phrase)
    @words_list = phrase.gsub(/\W/, ' ').downcase.split(' ')
  end

  def count
    words_list.uniq.reduce({}) do |word_counts, word|
      word_counts[word] = words_list.count(word)
      word_counts
    end
  end
end
