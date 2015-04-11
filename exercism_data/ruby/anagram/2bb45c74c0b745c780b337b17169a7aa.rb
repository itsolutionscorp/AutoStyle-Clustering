class Anagram
  def initialize base
    @base_word = base.downcase
  end

  def match word_list    
    word_list.select { |word| anagram? word.downcase }    
  end 

  private
  def anagram? word
    @base_word != word && sort_word(word) == sorted_base
  end

  def sorted_base
    @sorted_base ||= sort_word @base_word
  end

  def sort_word word
    word.each_char.sort
  end
end
