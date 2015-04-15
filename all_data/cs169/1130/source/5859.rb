def combine_anagrams(words)
 h1 = Hash.new {|hash,key| hash[key] = []}
 words.each{|palabra| clave=palabra.downcase.split(//).sort.join; h1[clave]=h1[clave]<<palabra }
 return h1.values
end

