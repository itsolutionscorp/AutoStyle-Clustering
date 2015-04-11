class Anagram
  def initialize(word)
    @word = word.downcase
  end
  
  def match(potential_anagrams)
    potential_anagrams.select {|test_word| anagram? test_word}
  end
  
  private
  def anagram?(test_word)
    count_letters(@word) == count_letters(test_word.downcase)
  end
  
  def count_letters(word_for_counting)
    Histogram.new(word_for_counting.chars).count
  end
end

class Histogram
  def initialize(items)
    @items = items
  end
  
  def count
    @items.each_with_object(Hash.new(0)) { |item, item_counts| item_counts[item] += 1}
  end
end
