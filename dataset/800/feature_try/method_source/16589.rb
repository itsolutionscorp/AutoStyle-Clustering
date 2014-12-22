def combine_anagrams(words)
  output = Hash.new
  arr = Array.new
  if (words.count > 0) then
    for i in (0..(words.count - 1)) do
      for j in (0..(words.count - 1)) do
        (w1 = words[i].downcase
        w2 = words[j].downcase
        if (i != j) then
          arr = Array.new
          if (output[w1.chars.sort.join] == nil) then
            arr.push(words[i])
            output[w1.chars.sort.join] = words[i]
          end
          if (output[w1.chars.sort.join] != nil) then
            arr = output[w1.chars.sort.join]
            if (not arr.include?(words[i])) and (w1.chars.sort.join == w2.chars.sort.join) then
              arr.push(words[i])
            end
          end
          output[w1.chars.sort.join] = arr
        end)
      end
    end
  end
  a = Array.new
  for key in output.keys do
    a.push(output[key])
  end
  a
end