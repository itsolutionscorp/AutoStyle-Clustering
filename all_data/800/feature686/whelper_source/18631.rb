def combine_anagrams(words)
  puts(words)
  maphash = Hash.new
  words.each do |w|
    wkey = w.downcase.chars.sort.join
    maphash.key?(wkey) ? (maphash[wkey].push(w)) : (maphash[wkey] = [w])
  end
  return maphash.values
end

