def combine_anagrams(words)
  l = {}
  final = []
  words.each do |word|
    w = word.chars.sort_by(&:downcase).join
    v = w.downcase
    if l[v] == nil
      l[v] = [word]
    else
      l[v].push(word)
    end
  end
  l.each do |nl|
    final.push( nl[1] )
  end
  return final
end
