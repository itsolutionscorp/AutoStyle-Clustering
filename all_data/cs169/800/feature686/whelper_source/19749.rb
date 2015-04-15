def combine_anagrams(words)
  myhash = {}
  aout = words.each do |aword|
    akey = aword.downcase.chars.sort.join
    myhash[akey].nil? ? (myhash[akey] = [aword]) : ((myhash[akey] << aword))
  end
  myhash.values
end

