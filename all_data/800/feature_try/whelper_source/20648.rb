def ana(string)
  return string.chars.sort { |a, b| a.casecmp(b) }.join.downcase
end

def combine_anagrams(words)
  res = Hash.new([])
  words.each do |word|
    el = ana(word)
    (res[el].length == 0) ? (res[el] = [word]) : ((res[el] << word))
  end
  res_ar = []
  res.each { |ar| (res_ar << ar[1]) }
  return res_ar
end

