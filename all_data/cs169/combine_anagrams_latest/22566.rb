def combine_anagrams(words)
    sorted = []
    words.each do |w|
        s = w.downcase.split('').sort.join
        puts s
        sorted.push([s, true])
    end
    output = []
    sorted.each_index do |i|
        if sorted[i][1]
        	e = [words[i]]
        	j = i + 1
        	while j < sorted.length
        		if sorted[j][0] == sorted[i][0]
        			sorted[j][1] = false
        			e.push(words[j])        			
        		end
        		j += 1
        	end
        output.push(e)
        end
    end
    output
end
