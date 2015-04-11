class Anagram
   def initialize (word)
      @word = word
   end

   def match (wordList)
      base = sort @word
      wordList.find_all do |word|
         base == sort(word) && @word.downcase != word.downcase
      end
   end

   private
   def sort (word)
      word.downcase.chars.sort.join ''
   end
end
