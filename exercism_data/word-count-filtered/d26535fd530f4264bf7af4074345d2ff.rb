class Phrase


 def initialize(string)

   @string = string

 end

   def word_count

     word_count = Hash.new(0)

     @string.split(/(?!')\W+/).each do |word|

     word_count[word.downcase] += 1

   end

   return word_count

 end

end
