
def combine_anagrams(words)
  #   <YOUR CODE HERE>
  map = {}
  words.each {|s|
    llave = s.downcase.chars.sort.join
    temp = {}
    if map.has_key?(llave)
      value = map[llave]     
 #     if value.index{|x| x.downcase == s.downcase} == nil
        temp= {llave => value + [s]}
 #     end
    else
      temp = {llave => [s]}
    end
    map.merge!(temp)  
    }
    return map.values
end
