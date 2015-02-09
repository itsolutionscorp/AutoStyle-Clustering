def combine_anagrams(words)
  if words.length == 0 then
   return []
  end
  ana = [[words[0]]]
  words.delete_at(0)
  while words.length > 0 do
    lt = words.length    
    ana.each do |a|
      reg = words[0].chars.sort{|a,b| a.casecmp(b)}.join
      if a[0].chars.sort{|d,e| d.casecmp(e)}.join =~ /^#{Regexp.quote(reg)}$/i 
        a << words[0] 
        words.delete_at(0)
        break
      end
    end
     if lt == words.length
      ana<<[words[0]]
      words.delete_at(0)
    end
  end
  return ana
end