def combine_anagrams(words)
  myhash = Hash.new
  words.each do |word|
    s = sort_str(word.downcase)
    myhash.has_key?(s) ? (myhash[s].push(word)) : (myhash[s] = [word])
  end
  myhash.values
end