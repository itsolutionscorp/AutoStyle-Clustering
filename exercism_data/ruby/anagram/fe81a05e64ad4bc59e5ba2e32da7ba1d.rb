class Anagram
   def initialize(word)
      @subject = word
   end

   def match(word_list)
      base = identity @subject
      word_list.find_all do |word|
         base == identity(word) && !same?(word)
      end
   end

   private
   def identity(word)
      word.downcase.chars.sort
   end

   def same?(word)
      @subject.downcase == word.downcase
   end
end
