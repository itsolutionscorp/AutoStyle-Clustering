class Anagram 
  def initialize(word)
    @actual_word = word
    @sorted_word = word.downcase.chars.sort
  end

  def match(list)
    selected = list.select { |word| word.downcase.chars.sort == @sorted_word }
    selected.delete_if { |word| word.downcase == @actual_word }
  end
end
