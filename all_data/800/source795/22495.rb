def combine_anagrams(words)
  dict={}
  words.each do |w|
    x=w.chars.sort.join.downcase
    if dict.has_key?(x)
      dict[x]=dict[x] << w
    else
      dict[x] = [w]
    end
  end
  return dict.values
end

