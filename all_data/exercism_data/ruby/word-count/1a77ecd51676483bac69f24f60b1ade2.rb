class Phrase
   def initialize (phrase)
      @phrase = phrase
   end

   def word_count
      count_words
   end

   private
   def count_words ()
      words = tokenize(@phrase)
      words.each_with_object(Hash.new(0)) { |word, word_count|
         word_count[word] += 1
      }
   end

   def tokenize (str)
      str.downcase.scan(/\w+/)
   end
end
