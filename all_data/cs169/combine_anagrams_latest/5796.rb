def combine_anagrams(words)
  a = Hash.new(0)
    words.each do |word|
      b = word.downcase.chars.sort.join
      a[b]=[]
    end
    words.each do |word|
      b = word.downcase.chars.sort.join
      a[b] << word
    end
    p a.values
end
