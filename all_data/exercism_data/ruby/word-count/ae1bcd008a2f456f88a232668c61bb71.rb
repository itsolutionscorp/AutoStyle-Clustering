class Phrase
   def initialize (phrase)
      @phrase = phrase
   end

   def word_count
      words = tokenize
      words.each_with_object(Hash.new(0)) do |word, word_count|
         word_count[word] += 1
      end
   end

   private
   def tokenize
      @phrase.downcase.scan(/\w+/)
   end
end
