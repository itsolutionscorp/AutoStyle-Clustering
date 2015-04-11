class Anagram
  def initialize word
    @word = word.to_s.downcase
  end
  
  def match word_list
    filter_anagrams word_list
  end
  
  private
  def is_anagram? anagram
    @word != anagram && sorted_word == sort_string(anagram)
  end
  
  def sorted_word
    @sorted_word ||= sort_string(@word)
  end
  
  def downcase_list list
    list.each.map(&:to_s).map(&:downcase)
  end
  
  def filter_anagrams list
    downcase_list(list).select {|anagram| is_anagram? anagram }
  end
  
  def sort_string string
    string.split(//).sort
  end
end
