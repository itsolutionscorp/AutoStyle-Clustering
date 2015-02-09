def combine_anagrams(words)
  
  anagrams = Hash.new();
  
  returnArray = Array.new();
  
  #creates an array of uncapitalized sorted letters
  words.each{ |value|
  
    tempString = value.downcase();
    
    x = tempString.split(//).sort();
    
    tempString = x.join();
    if(anagrams[tempString] == nil)
      anagrams[tempString] = Array.new();
     end
    anagrams[tempString] = anagrams[tempString].push(value);
    
  }
  
  anagrams.each{|key , value|
    returnArray = returnArray.push(value);

  }
  
  print(returnArray);
  return returnArray;
end


