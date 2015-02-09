def combine_anagrams(words)
  res = Hash.new
  words.each do | word |
    inp = word.downcase.chars.sort.join
    if res.has_key?(inp)
      res[inp].push(word)
    else
      res[inp] = [ word ]
    end
  end
  
  final = Array.new
  res.each do |key, value|
    final.push value
  end
  final
end
