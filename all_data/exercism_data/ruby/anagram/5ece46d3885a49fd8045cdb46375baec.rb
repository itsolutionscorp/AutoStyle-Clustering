class Anagram
  def initialize word
    @word = word.to_s
  end
  
  def match word_list
    filter_anagrams word_list
  end
  
  def is_anagram? anagram
    lowercase_word != anagram.to_s.downcase &&
    sorted_word == sort_string(anagram.to_s.downcase)
  end
  
  private
  def lowercase_word
    @lowercase_word ||= @word.downcase
  end
  def sorted_word
    @sorted_word ||= sort_string(lowercase_word)
  end
  
  def filter_anagrams list
    list.select {|anagram| is_anagram? anagram }
  end
  
  def sort_string string
    string.split(//).sort
  end
end
