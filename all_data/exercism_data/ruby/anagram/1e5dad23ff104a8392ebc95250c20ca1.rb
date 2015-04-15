class Anagram

  def initialize( word )
    @base_word        = word.downcase
    @sorted_base_word = downcase_sort( word )
    @word_list        = []
  end

  def match( word_list )
    word_list.each do |word| 
      @word_list << word  unless word.downcase == @base_word
    end
    match_words
  end

  def match_words
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
