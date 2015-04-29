def anagrams?(word0, word1)
  word0.downcase.split('').sort == word1.downcase.split('').sort
end

def headtail(words)
sorted = []
  if words.size == 1
    @result << words
  elsif (words.size == 0) || (words == nil)
    return nil
  else
    b = []
    first = words.first
    words.delete_at(0)
    words.each { |word| b << anagrams?(first, word) << word }
    words = []
    non_anagrams = []
    anagrams = []

    b.each_slice(2) do |bool, word|
      non_anagrams << [bool, word] if bool == false
      anagrams << [bool, word] if bool == true
    end

    anagrams.each { |anagram| sorted << anagram[1] }
    non_anagrams.each { |word| words << word[1] }

    sorted << first
    @result << sorted
    headtail(words)
  end
end

def combine_anagrams(words)
  @result = []
  headtail(words)
  return @result
end
