def combine(popped, found)
  puts(popped.inspect)
  puts(found.inspect)
  solution = []
  solution.push(popped[0])
  found.each { |elem| solution.push(elem[0]) }
  return solution
end

def combine_anagrams(words)
  solution = []
  words_y_descomp = words.collect { |elem| [elem, elem.upcase.scan(/./).sort] }
  while (words_y_descomp.length > 0) do
    ultim = words_y_descomp.pop
    iguales = words_y_descomp.collect { |elem| elem if (elem[1] == ultim[1]) }
    words_y_descomp = (words_y_descomp - iguales)
    iguales.delete(nil)
    solution.push(combine(ultim, iguales))
  end
  return solution
end

