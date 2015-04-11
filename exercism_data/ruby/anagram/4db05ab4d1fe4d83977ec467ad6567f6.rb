class Anagram
  def initialize(word)
    @word = word
    @sorted = sort_chars(word)
  end

  def match(words)
    words.select do |w| 
      same_length?(w) && different?(w) && match_sorted?(w) 
    end
  end

  def same_length?(test)
    @word.length == test.length
  end

  def different?(test)
    @word.downcase != test.downcase
  end

  def match_sorted?(test)
    @sorted == sort_chars(test)
  end

  def sort_chars(word)
    word.downcase.chars.sort.join('')
  end
end
