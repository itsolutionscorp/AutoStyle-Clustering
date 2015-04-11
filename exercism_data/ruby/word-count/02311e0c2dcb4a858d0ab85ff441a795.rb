class Phrase
   def initialize (phrase)
      @phrase = phrase
   end

   def word_count
      words = tokenize(@phrase)
      words.each_with_object(Hash.new(0)) { |word, word_count|
         word_count[word] += 1
      }
   end

   private
   def tokenize (str)
      str.downcase.scan(/\w+/)
   end
end
