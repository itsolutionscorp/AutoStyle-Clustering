# 3.
def combine_anagrams(words)
  #   <YOUR CODE HERE>
  words2 = words.dup
  words2.map do |elt|
    d = elt.to_a
    c = elt.chars.to_a.sort.to_s.downcase
    #puts "1st " + elt
    words2.each do |elt2|
      #puts "2nd " + elt2
      if elt2.chars.to_a.sort.to_s.downcase == c and elt!=elt2
        #puts "3rd " + elt2
        d << elt2
        words2.delete(elt2)
      end
    end
    #puts words.length
    #if words.class == String or (words.class==Array and words.flatten.length==1)
    #  d[0]
    #else
      d
    #end
  end
end

# the problem is, "words" is actually deleted!
