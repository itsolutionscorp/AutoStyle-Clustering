def combine_anagrams(words)

words_alpha_rearr=[]

words.each {|x| words_alpha_rearr << x.chars.sort_by(&:downcase).join.downcase }

words_alpha_rearr_unique=words_alpha_rearr.uniq

output=Array.new(words_alpha_rearr_unique.count)

words_alpha_rearr_unique.each_with_index {|z,uidx|

results_with_index = words_alpha_rearr.each_with_index.select { |i, idx| i==z}
results_with_index.map!{|i|i[1]}

output[uidx]=Array.new(results_with_index.count)

g=0
results_with_index.each {|p,idx|
 output[uidx][g]=words[p]
 g+=1
}
}
return output
end

