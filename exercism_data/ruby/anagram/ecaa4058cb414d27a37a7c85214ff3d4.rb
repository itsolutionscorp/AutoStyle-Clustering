class Anagram
  attr_accessor :a_word
  def initialize(word="")
    @a_word = word
  end
  def match(word_arr)
    match_arr = Array.new
    word_arr.each do |w|
      if (is_anagram?(w))
        match_arr.push(w)
      end
    end
    match_arr
  end
  private
  def is_anagram?(word)
    temp_word = word.downcase
    return false if @a_word == temp_word #don't allow identical words as anagrams
    @a_word_hash = letter_count_hash(@a_word.downcase)
    word_hash = letter_count_hash(temp_word)
    return false unless same_number_of_letters?(@a_word_hash,word_hash)
    bool_flag = true
    word_hash.each do |key,val|
      unless @a_word_hash.key?(key)&&@a_word_hash[key]==val
        bool_flag = false
      end
    end
    return bool_flag
  end
  def same_number_of_letters?(word1_hash,word2_hash)
    word1_hash.keys.length==word2_hash.keys.length
  end
  def letter_count_hash(word)
    #{"dab"=>1,"bad"=>3,"etc"=>4}
    @letter_hash = Hash.new
    word.each_char do |letter|
      @letter_hash[letter] = (@letter_hash[letter].nil?) ? 0 : @letter_hash[letter]+1
    end
    @letter_hash
  end
end
