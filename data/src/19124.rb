def combine_anagrams(words)
  myhash = {}
  words.each do |word|
    sorted = word.downcase.split(/\s*/).sort.join
    if myhash.key?(sorted) then
      myhash[sorted] = myhash[sorted], word
    else
      newhash = { sorted => (word) }
      myhash = myhash.merge(newhash)
    end
  end
  myarray = []
  myhash.each_value { |value| (myarray << value) }
  return myarray
end