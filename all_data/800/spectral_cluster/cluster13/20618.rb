def combine_anagrams(words)

  groups = Array.new
  words_srt = Array.new(words)
  words_srt.collect! { |x| x = x.downcase; x = x.chars.sort.join; }
  words_srt_unq=words_srt.uniq

  i=0
  while (i<words_srt_unq.length)
  tmp_group = Array.new
  j=0
    while(j<words_srt.length)  
      if (words_srt[j] == words_srt_unq[i]) then 
      tmp_group = tmp_group + [words[j]]; end 
      j += 1
    end
  groups[i] = tmp_group                     
  i += 1
  end
  return groups
end




