def combine_anagrams(words)
  words.each_with_object(Hash.new{|h,k| h[k]=[]}) do |m,h| 
    h[m.downcase.chars.sort.join] << m
  end.values
end
