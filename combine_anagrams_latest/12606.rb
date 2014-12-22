def combine_anagrams(words)
  cloned = words.clone
  allword = words.collect {|word|
    word.downcase.split(//).sort.join
  }
  combined = Hash.new()
  allword.uniq.each{|word|
    combined[word] = Array.new
  }
  words.each{|eachword|
    combined[eachword.downcase.split(//).sort.join] << eachword
  }
  combined.collect{|key, value|
    value
  }
end
puts combine_anagrams(['cARs', 'for', 'potatoes', 'rAcs', 'four','scar', 'creams', 'scream']).inspect
#puts combine_anagrams([['HeLLo', 'hello']).sort