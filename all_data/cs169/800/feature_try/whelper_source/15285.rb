def is_anagrams?(word1, word2)
  if (word1.length != word2.length) then
    return false
  else
    word1 = word1.downcase
    word2 = word2.downcase
    h1 = Hash.new
    for i in (0..(word1.length - 1)) do
      (ch = word1[i]
      h1[ch].nil? ? (h1[ch] = 1) : (h1[ch] = (h1[ch] + 1)))
    end
    h2 = Hash.new
    for i in (0..(word2.length - 1)) do
      (ch = word2[i]
      h2[ch].nil? ? (h2[ch] = 1) : (h2[ch] = (h1[ch] + 1)))
    end
    h1.keys.each do |tmp|
      if ((h2[tmp].nil? == true) or h1[tmp].eql?((h2[tmp] == false))) then
        return false
      end
    end
    return true
  end
end

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

