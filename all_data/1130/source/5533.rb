def combine_anagrams(words)
retHash = Hash.new

words.map{ |wd| 
		keyAr = wd.downcase.each_char.to_a.sort
		keyH = ""
		keyAr.map{ |c| keyH << c}
		retHash.key?(keyH)? retHash[keyH].push(wd):retHash[keyH]=Array.new(1,wd);
}

retArr = Array.new
retHash.each_value{ |v| retArr.push(v)}

return retArr
end

 combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])