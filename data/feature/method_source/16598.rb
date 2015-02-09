def combine_anagrams(words)
  output = []
  words.each do |x|
    exist = false
    outputAux = Array.new(output)
    output.each do |y|
      if (y[0].downcase.split(//).sort == x.downcase.split(//).sort) then
        outputAux.delete(y)
        (outputAux << (y << x))
        exist = true
      end
    end
    output = outputAux
    (output << [x]) if (exist == false)
  end
  return output
end