def combine_anagrams(words)
  z = {}
  words.each do |b|
	 #puts b.chars.sort.join
     if z.has_key?(b.downcase.chars.sort.join)
        z[b.downcase.chars.sort.join] += [b]
     else
        z[b.downcase.chars.sort.join] = [b]
     end
  end
  y = z.to_a
  #puts y
  d = []
  y.each do |e|
    #puts e[1]
    d.push(e[1])
	#puts "---"
  end
  return d
end

#puts combine_anagrams(["potS", "spot", "stop", "tOps", "tops", "STOPS", "spots", "sAUsage"])
