def combine_anagrams(words)
  if (words.length > 0) then
    newword = words[0].downcase.chars.sort_by(&:downcase).join
  else
    return []
  end
  a = [["dummy"]]
  a[0][0] = newword
  words.each do |word|
    b = 0
    newword = word.downcase.chars.sort_by(&:downcase).join
    for w in (0..(a.length - 1)) do
      if (a[w][0] == newword) then
        a[w].insert(a[w].length, word)
        b = 1
      end
    end
    if (b == 0) then
      newarray = Array.new
      newarray[0] = newword
      newarray[1] = word
      a.insert(a.length, newarray)
    end
  end
  a.each { |c| c.delete_at(0) }
  puts(a)
  return a
end