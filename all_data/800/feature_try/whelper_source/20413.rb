def combine_anagrams(words)
  gram = Array.new
  words.each { |word| (gram << [word.downcase.chars.sort.join, gram.length]) }
  gram.sort!
  out = Array.new
  temp = Array.new
  for i in (1..gram.length) do
    (temp.push(words[gram[(i - 1)][1]])
    if (i == gram.length) then
      out.push(temp.compact)
      break
    else
      if (gram[(i - 1)][0] == gram[i][0]) then
        next
      else
        out.push(temp.compact)
        temp.clear
      end
    end)
  end
  return out
end

