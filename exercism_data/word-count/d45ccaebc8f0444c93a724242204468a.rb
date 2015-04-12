class Words
  attr_reader :words_list, :word_counts

  def initialize(phrase)
    @words_list = phrase.gsub(/\W/, ' ').downcase.split(' ')
    @word_counts = {}
  end

  def count
    words_list.uniq.each { |word| count_occurences_of(word) }
    word_counts
  end

  def count_occurences_of(word)
    @word_counts[word] = words_list.count(word)
  end
end
