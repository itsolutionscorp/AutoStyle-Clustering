#metaprogramming to add the method "is_anagram?" to check
#if the string and the passed string have the same letters
class String
  def is_anagram?(w)
    h1 = {}
    self.downcase.each_byte {|b| h1[b] ||= 0; h1[b] += 1}
    
    #do the same thing for the word
    h2 = {}
    w.downcase.each_byte {|b| h2[b] ||= 0; h2[b] += 1}
    
    h1 == h2
  end
end

class Array
  #this method is finding anagrams of the word "w" and
  #pass the result as an array
  def find_anagrams(w)
    anagrams = []
    self.each do |word|
      if w.is_anagram? word
        anagrams << word
      end
    end
    anagrams
  end
  
end
       
def combine_anagrams(words)
  result=[]
  
  words.each do |w|
    #check if the word is NOT already included in result
    if !result.flatten.include?(w)
      result << words.find_anagrams(w)
    end
  end
  result
end

#test
puts "is the 'String#is_anagram?' working? #{"ciao".is_anagram?("icao")}" 

words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
puts combine_anagrams(words).inspect