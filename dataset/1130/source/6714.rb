def combine_anagrams(words)
  output = Array.new
  words.each do |word|
    found = false
    output.each do |group|
      if group[0].downcase.chars.sort.join == word.downcase.chars.sort.join
         found = true
	 group.push word
      end
    end
    if found == false
      newgroup = Array.new
      newgroup.push word
      output.push newgroup
    end
  end
  output
end

