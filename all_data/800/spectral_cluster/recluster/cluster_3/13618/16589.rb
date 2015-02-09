def combine_anagrams(words)
  output = Hash.new
  arr = Array.new
  if words.count > 0
    for i in 0..words.count-1
      for j in 0..words.count-1
        w1 = words[i].downcase
        w2 = words[j].downcase
        if (i != j)
          arr = Array.new
          if (output[w1.chars.sort.join] == nil)
            arr.push(words[i])
            output[w1.chars.sort.join] = words[i]
          end

          if (output[w1.chars.sort.join] != nil)
            arr = output[w1.chars.sort.join]

            if (!arr.include?(words[i]) && (w1.chars.sort.join == w2.chars.sort.join))
              arr.push(words[i])
            end
          end
          output[w1.chars.sort.join] = arr
        end
      end
    end
  end

  a = Array.new
  for key in output.keys
    a.push(output[key])
  end
  a
end



#input =['HeLLo', 'hello']
##input = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#out = combine_anagrams(input)
#
#print out[0][0]