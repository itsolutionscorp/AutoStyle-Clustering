class Anagram
  def initialize(root_word)
    @root_word = root_word
  end
  
  def match(possible_matches)
    @possible_matches = possible_matches
    cleaned_possibles.delete_if { |possible| arrays_mismatch? possible }
  end
  
  private
  
  def root_word_to_sorted_array
    @root_word.downcase.split('').sort
  end
  
  def arrays_mismatch?(possible)
    possible.downcase.split('').sort != root_word_to_sorted_array
  end
  
  def cleaned_possibles
    @possible_matches.uniq { |p| p.downcase }.delete_if { |possible| possible == @root_word }
  end
end
