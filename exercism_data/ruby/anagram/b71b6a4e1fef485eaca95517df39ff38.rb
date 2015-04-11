class Anagram
   def initialize(word)
      @subject = word
   end

   def match(word_list)
      base = ident @subject
      word_list.find_all do |word|
         base == ident(word) && !same?(word)
      end
   end

   private
   def ident(word)
      word.downcase.chars.sort
   end

   def same?(word)
      @subject.downcase == word.downcase
   end
end
