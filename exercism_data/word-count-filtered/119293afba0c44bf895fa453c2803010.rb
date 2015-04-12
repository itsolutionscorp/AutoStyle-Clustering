class Phrase


 def initialize(string)

   @string = string

 end

   def word_count

     count = 0

     word_count = {}

     @string.split.each do |word|

       if word.downcase == word.downcase
         count += 1
         word_count[word] = count
       end

   end

   return word_count

 end

end
