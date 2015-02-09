def combine_anagrams(words)
 aux=[]
 words.each{
  |word| name=(word.downcase.split('').sort.join('').delete(" "))
  if not aux.include?(name) then aux.push(name) end
 }
 respuesta=[]
 aux.each{
  |palabra| temp=[] 
  words.each{
     |word| if (palabra).eql?(word.downcase.split('').sort.join('').delete(" ")) then temp.push(word) end 
  }
  respuesta.push(temp) 
 }
 return respuesta
end

#combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams','scream'])
