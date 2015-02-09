def combine_anagrams(words)
  myhash = {}
  words.each do |word|
    wrd = word.downcase.scan(/\w/).sort.join
    if myhash.key?(wrd)
      myhash[wrd].push(word)
    else
      myhash[wrd] = [word]
    end
  end
  myhash.values
end