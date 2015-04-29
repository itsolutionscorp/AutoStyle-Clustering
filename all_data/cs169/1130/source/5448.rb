#! /usr/bin/ruby

def combine_anagrams(words)
    arr = words.map do |word|
       words.select { |word1| word.downcase.split("").sort.join ==
	    word1.downcase.split("").sort.join }
    end.uniq
end

#puts combine_anagrams(['cars', 'For', 'poTAToes', 'rAcs', 'four','SCaR', 'creams', 'scream']).to_s
