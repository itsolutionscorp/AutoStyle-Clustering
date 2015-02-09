=begin
# input:
['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],
["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
=end
input= ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
def combine(popped,found)
  puts popped.inspect
  puts found.inspect
  solution = []
  solution.push popped[0]
  found.each do |elem|
    solution.push elem [0]
  end
  return solution
end

def combine_anagrams(words)
  solution = []
  words_y_descomp = words.collect { |elem| [elem,elem.upcase.scan(/./).sort]}
  while words_y_descomp.length>0 do
    ultim = words_y_descomp.pop
    iguales = words_y_descomp.collect{ |elem| elem if elem[1]==ultim[1]}
    #iguales tiene el tamaño de words_y_descomp
    words_y_descomp = words_y_descomp-iguales
    iguales.delete(nil)
    solution.push combine(ultim,iguales)
  end
  #puts iguales.inspect
  return solution
end
#puts combine_anagrams(input).inspect


