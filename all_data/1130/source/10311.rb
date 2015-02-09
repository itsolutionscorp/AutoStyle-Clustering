def combine_anagrams(words)
  result = Hash.new(Array.new);
  words.each{|word|
    print "Checking " + word + "\n"
    wordDown = word.downcase
    if not result.has_key?(wordDown.chars.sort)
      print "Creating " + word + "\n"
      result[wordDown.chars.sort] = Array.new;
    end
    result[wordDown.chars.sort] << word
  }
  result2 = []
  result.each_pair{ |key, value| result2.push(value) }
  print result2
  result2
end  

proof = ["cars", "for", 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
proof2 = ["hello", "heLLo"]
combine_anagrams(proof2)