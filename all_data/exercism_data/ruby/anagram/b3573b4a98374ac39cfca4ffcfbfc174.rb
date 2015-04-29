class Anagram
  def initialize(word)
    @word = word
  end
  def rearrange(word)
    word.downcase.each_char.sort.join
  end

  def match(possible_anagrams)
    collection = []
    word = rearrange(@word)
    possible_anagrams.each do |value|
      if rearrange(value) == word && @word != value.downcase
        collection << value
      end
    end
    collection
  end
  
end
