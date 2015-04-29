class Anagram

  attr_accessor :word, :word_list

  def initialize(word)
    @word = word.downcase
  end

  def initializei(word_list)
    self.word_list = word_list 
  end

  def match(word_list)
    matched = []
    word_list.each do |entry|
      if entry.downcase.each_char.to_a.sort == @word.each_char.to_a.sort and entry.downcase. != word
        matched.push(entry)
      end
    end
    return matched
  end

end
