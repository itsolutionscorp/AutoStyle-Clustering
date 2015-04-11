class WordSorter
  def self.call(word)
    word.split(//).sort.join ''
  end
end

class Anagram

  def initialize(word)
    @word = word
  end

  def match(words)
    words.group_by do |word| 
      WordSorter.call(@word.downcase) == 
        WordSorter.call(word.downcase) unless same_word?(word)
    end[true] || []
  end

  def same_word?(word)
    @word == word || 
      @word.downcase == word || 
      @word.upcase == word ||
      @word.capitalize == word
  end

end
