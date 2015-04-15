def combine_anagrams(words)
  output = []
  words.each do |t|
    yeah = 0
    output.each do |o|
      if(o[0].downcase.split(//).sort.eql? t.downcase.split(//).sort)
        o.push(t)
        yeah = 1
      end
    end
    if(yeah == 0); output.push([t]) ; end
  end
  return output
end

words =  ['cars','for','potatoes','racs','four','scar','creams','scream']

puts "#{combine_anagrams(words)}"
