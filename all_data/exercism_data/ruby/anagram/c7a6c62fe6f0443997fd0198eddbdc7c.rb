class Anagram
  def initialize(word)
    @anagrams = word.downcase.chars.to_a.permutation.map(&:join)
    @anagrams.delete(word) #remove original word
  end
  
  def match(options)
    options.each_with_object([]){|option, matches| if @anagrams.include? option.downcase then matches << option end }
  end
end
