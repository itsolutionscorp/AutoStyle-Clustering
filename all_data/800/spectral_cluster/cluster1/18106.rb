def combine_anagrams(words)
        ret = {}
        words.each{ |w|
			grp = w.downcase.split(//).sort.join
			if ret.has_key?(grp) then
					ret[grp].insert(-1, w)
			else
					ret[grp] = [w]
			end
        }
        ret.values
end


