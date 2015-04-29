class Anagram

  def initialize word
    @word = word
    @encoded_word = encoded_word word
  end

  def match list
    list.select {|try| anagram_matches? try }
  end

  def anagram_matches? try
    return false if same_word? try
    
    @encoded_word == encoded_word(try)
  end

  private

  def same_word? try
    @word.downcase == try.downcase
  end

  def encoded_word word
    word.chars
        .group_by(&:downcase)
        .map {|k,v| [k, v.count] }
        .sort
  end
end
