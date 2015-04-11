class Anagram

  def initialize( word )
    @base_word        = word.downcase
    @sorted_base_word = downcase_sort( word )
    @word_list        = []
  end

  def match( words )
    remove_words_identical_to_base_word( words )
    find_anagrams_of_base_word
  end

  def remove_words_identical_to_base_word( words )
    words.each do |word| 
      @word_list << word  unless word.downcase == @base_word
    end
  end

  def find_anagrams_of_base_word
    anagram_list = []
    @word_list.each_index do |x|
      anagram_list << @word_list[x]  if downcase_sort( @word_list[x] ) == @sorted_base_word
    end
    anagram_list.uniq
  end
  
  def downcase_sort( word )
    word.downcase.chars.sort.join  
  end
end
