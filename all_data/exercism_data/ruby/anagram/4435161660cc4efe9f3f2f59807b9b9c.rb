class Anagram

  def initialize ana_word
    @ana_word = ana_word
  end

  def match word_list
    word_list.select {|word|
      @ana_word.downcase.chars.sort.join == word.downcase.chars.sort.join \
      && @ana_word.downcase != word.downcase
    }
  end

end
