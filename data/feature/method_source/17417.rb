def combine_anagrams(words)
  p(words)
  arr_words = Array([])
  words.each do |i|
    flag = false
    for j in (0..(arr_words.length - 1)) do
      (if (not flag) then
        if (arr_words[j][0].chars.sort.join == i.chars.sort.join) then
          arr_words[j].push(i)
          flag = true
        end
      end
      break if flag)
    end
    arr_words.push(Array[i]) if (not flag)
  end
  p(arr_words)
end