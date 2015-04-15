class Words
  attr_reader :words_list, :word_counts

  def initialize(phrase)
    @words_list = phrase.gsub(/\W/, ' ').downcase.split(' ')
    @word_counts = {}
  end

  def count
    words_list.uniq.each { |word| set_hash_item_for(word) }
    word_counts
  end

  def set_hash_item_for(word)
    @word_counts[word] = words_list.grep(word).size
  end
end
