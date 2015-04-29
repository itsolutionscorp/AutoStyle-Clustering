class Anagram
  attr_accessor :a_word
  def initialize(word="")
    @a_word = word.downcase
  end
  def match(word_arr)
    match_arr = Array.new
    word_arr.each do |w|
      if (word_is_anagram?(w))
        match_arr.push(w)
      end
    end
    match_arr
  end
  private
  def word_is_anagram?(word)
    word = word.downcase
    @a_word_hash = letter_count_hash(@a_word.downcase)
    word_hash = letter_count_hash(word)
    bool_flag = true
    bool_flag = false if @a_word == word
    word_hash.each do |key,val|
      if (!@a_word_hash.has_key?(key)||@a_word_hash.keys.length!=word_hash.keys.length||@a_word_hash[key]!=val)
        bool_flag = false
      end
    end
    return bool_flag
  end
  def letter_count_hash(word)
    @letter_hash = Hash.new
    word.each_char do |letter|
      @letter_hash[letter] = (@letter_hash[letter].nil?) ? 0 : @letter_hash[letter]+1
    end
    @letter_hash
  end
end
