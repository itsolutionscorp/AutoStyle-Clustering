#Chris Rockwell 1-3
def combine_anagrams(words)
  srtd = []
  out =[]
  words.each do |w| srtd << w.downcase.chars.sort.join; end
  srtd.uniq!
  
  srtd.each do |g|
    out << []   
  end
    
  words.each do |word| 
  i=0; num = srtd.size();
    while i < num
      g = srtd[i]
      if (word.downcase.chars.sort.join == g)
        out[i] << word
      end  
      i =i+1;
    end    
  end
    
 return out
end

input = ['cars', 'CARS','for', 'potatoes', 'racs', 'four', 'scar', 'creams', 'scream']
bl= []
puts combine_anagrams(input)
puts combine_anagrams(bl)

