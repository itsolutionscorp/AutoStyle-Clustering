def combine_anagrams(words)

orderedInput = []
output=[]
words.each {|word|
 orderedInput.push(word.downcase.chars.sort.join)
}

orderedInput.each_with_index{|blurb, i|

   if blurb != nil
      
      tmp_a = [];
      tmp_a.push(words[i])
      orderedInput[i] = nil

      orderedInput.each_with_index{|needle, j|

         if blurb == needle

             tmp_a.push(words[j])
             orderedInput[j] = nil
         end

      }
    
    output.push(tmp_a);
    end
}

output
end
