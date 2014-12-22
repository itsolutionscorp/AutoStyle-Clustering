def combine_anagrams(words)

ana=Hash.new
words.each do |word| order=word.downcase.chars.sort.join
  if ana[order]==nil then ana[order]=[word] else ana[order].push(word); end
  end

list=[]
ana.each do|key| list.push(key[1]);end
list
end