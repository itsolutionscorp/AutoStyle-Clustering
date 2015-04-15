def combine_anagrams(words)
  char = Array.new
  size = words.size
  hash = Hash.new
  counter = 0
  #words.each_with_index {|val, idx| hash[val.scan(/./).sort.to_s] = idx}
  for i in 0 ... size
    char[i] = words[i].downcase.scan(/./).sort
    ord = String.new
    char[i].each {|s| ord += s}

    if hash[ord] == nil
      hash[ord] = counter
      counter += 1
    end
  end
  
  match = Array.new(counter) { Array.new}
  
  for i in 0 ... size
    char[i] = words[i].downcase.scan(/./).sort
    ord = String.new
    char[i].each {|s| ord += s}

    match[hash[ord]].push(words[i])
  end
  
  
  match
end


#"Hello, world".scan(/./)
