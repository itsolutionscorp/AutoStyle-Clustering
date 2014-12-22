def combine_anagrams(words)
  a = []
  words.each do |word|
    ck = false
    for i in (0..(a.length - 1)) do
      if is_anagrams?(word, a[i][0]) then
        a[i] = (a[i] + [word])
        ck = true
        break
      end
    end
    a = (a + [[word]]) if (ck == false)
  end
  return a
end