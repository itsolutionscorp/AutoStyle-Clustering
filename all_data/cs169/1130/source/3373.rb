def combine_anagrams(words)
  a = {}
  words.each do |word|
     hsh = word.downcase.split("").sort.join("")
     a[hsh] = [] if a[hsh].nil?
     a[hsh] << word
  end
  a.map{|c| c[1]}
end