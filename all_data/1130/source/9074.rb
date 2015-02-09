class String
  def sort() 
    return self.chars.sort.join
  end
end

def combine_anagrams(words)

wordHash = Hash.new
words.each do |x|
xTemp = x.downcase

    if(wordHash[xTemp.to_s.sort] == nil)
      currentArray = Array.new
      currentArray<<x
      wordHash[xTemp.to_s.sort]=currentArray
    else
      wordHash[xTemp.to_s.sort]<< x
    end

  
end
return wordHash.values
end


