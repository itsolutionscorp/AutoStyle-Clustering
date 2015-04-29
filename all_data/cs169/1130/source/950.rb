def combine_anagrams(words)
  # input is expected to be an array
  hash = Hash.new
  words.each do |x|
    z = x.downcase.split("").sort.join
    if (hash[z] ==  nil)
      a = Array.new
    else
      a = hash[z]
    end
    a.push x
    hash[z] = a
  end
  # collect the values from the hash into an array
  b = hash.collect {|key,val| val}
  return b
end
