def combine_anagrams(words)
  res = Array.new
  words.each do |w|
    encontrado = false
    res.each do |a|
      if (a[0].chars.sort == w.chars.sort) then
        a.push(w)
        encontrado = true
      end
    end
    res.push(Array[w]) if (not encontrado)
  end
  return res
end

