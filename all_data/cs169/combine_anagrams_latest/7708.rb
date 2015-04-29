def combine_anagrams(words)
   retAna = Array.new
     words.each do |c|
         retAna << stringMatching(words, c)
     end
     retAna.uniq!
     return retAna
 end
 

 def stringMatching (words, word)
     retArry = Array.new
     g = word.split(//).sort
     words.each do |t|
         k = t.split(//).sort
         if (g.eql?(k))
           retArry << t
         end
     end
     return retArry
 end

