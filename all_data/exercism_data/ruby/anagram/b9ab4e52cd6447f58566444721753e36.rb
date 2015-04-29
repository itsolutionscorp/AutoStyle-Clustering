class Anagram
   def initialize(word)
      @word = word
   end

   def match(word_list)
      base = ident @word
      word_list.find_all do |word|
         base == ident(word) && !is_same(word)
      end
   end

   private
   def ident(word)
      word.downcase.chars.sort
   end

   def is_same(word)
      @word.downcase == word.downcase
   end
end
