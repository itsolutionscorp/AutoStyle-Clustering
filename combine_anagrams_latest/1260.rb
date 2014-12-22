def combine_anagrams(words)
    dict = {}
    words.each do |s|
    	sorted = s.downcase.split(//).sort.join("")
	dict[sorted] = (dict[sorted] || []).push(s)
    end
    return dict.values
end
