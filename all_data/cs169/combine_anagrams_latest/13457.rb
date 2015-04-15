def combine_anagrams(words); answer=Array.new; values=Array.new; sorted=Hash.new; words.each do|n|; sorted["#{n}"]=n.downcase.scan(%r[.]); end; sorted.each_value do|x|; x.sort!; end; values = sorted.values.uniq; values.each do |y|; answer[values.index(y)]=Array.new; words.each do |z|; if sorted[z]==y; answer[values.index(y)].push(z) end; end; end; return answer; end

