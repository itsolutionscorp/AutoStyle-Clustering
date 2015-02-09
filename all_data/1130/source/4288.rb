def combine_anagrams(words)
  anagrams = Hash.new();
  t = Hash.new();
  a = []
  final = []
  works = words.group_by { |word| word.chars.sort}.values
  words.each{
    |k|
    #return k.chars.sort.join
    if(!a.include? k.chars.sort.join)
      a << k.chars.sort.join
      anagrams[k] = k.chars.sort.join
    else
      t[k] = k.chars.sort.join
    end
    
    
  }
  #t {"scar"=>"acrs", "racs"=>"acrs", "scream"=>"acemrs"}
  #anagrams {"potatoes"=>"aeoopstt", "creams"=>"acemrs", "four"=>"foru", "for"=>"for", "cars"=>"acrs"}
  anagrams.each{
    |k,v|
    t.each{
      |key, value|
      if(v == value)
        #puts key
      else
        final << key 
      end
    }
  }
 # return anagrams
 #return final
 return works
end
#[["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]
#words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#p combine_anagrams(words)