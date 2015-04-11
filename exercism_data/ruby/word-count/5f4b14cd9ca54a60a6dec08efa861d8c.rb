class Phrase
   def initialize (phrase)
      @word_count = Hash.new(0)

      count_words(phrase)
   end

   def word_count
      @word_count
   end

   private
   def count_words (phrase)
      words = tokenize(phrase)
      words.each{ |word|
         @word_count[word] += 1
      }
   end

   def tokenize (str)
      return str.downcase.scan(/\w+/)
   end
end
