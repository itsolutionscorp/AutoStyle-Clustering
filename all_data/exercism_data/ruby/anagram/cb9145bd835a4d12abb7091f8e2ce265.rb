class Anagram
  def initialize word
    @word = word.downcase
  end
  
  def match potential_anagrams
    potential_anagrams.each_with_object(Array.new()) {
      |test_word, found_anagrams|
      found_anagrams << test_word if test_anagram test_word
    }
  end
  
  private
  def test_anagram test_word
    return (count_letter_instances @word).eql? (count_letter_instances test_word.downcase)
  end
  
  def count_letter_instances word_for_counting
    Histogram.new(word_for_counting.chars).count
  end
end

class Histogram
  def initialize items
    @items = items
  end
  
  def count
    @items.each_with_object(Hash.new(0)) { |item, item_counts| item_counts[item] += 1}
  end
end
