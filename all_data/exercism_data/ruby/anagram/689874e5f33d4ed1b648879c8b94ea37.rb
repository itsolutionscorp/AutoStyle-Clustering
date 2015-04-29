class Anagram

  attr_reader :word

  def initialize(word)
    @word = word.downcase
  end

  def same_letters?(entry)
    entry.downcase.each_char.sort == @word.each_char.sort
  end
  
  def identical_word?(entry)
    entry.downcase != word
  end

  def match(word_list)
    matched = []
    word_list.each do |entry|
      if same_letters?(entry) and identical_word?(entry)
        matched.push(entry)
      end
    end
    return matched
  end

end
