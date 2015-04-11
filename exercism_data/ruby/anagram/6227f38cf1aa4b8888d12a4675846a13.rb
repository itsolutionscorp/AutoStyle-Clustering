class Anagram
  def initialize base
    @base_word = base.downcase
  end

  def match word_list    
    word_list.select { |word| anagram? word.downcase }    
  end 

  private
  def anagram? word
    @base_word != word && sorted_chars(word) == sorted_base
  end

  def sorted_base
    @sorted_base ||= sorted_chars @base_word
  end

  def sorted_chars word
    word.each_char.sort
  end
end
