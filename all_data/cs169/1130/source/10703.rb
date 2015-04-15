def combine_anagrams(words)
  ag = Hash.new
  sw = String.new
  words.each do |word|
    a = word.downcase.each_char.sort
    sw = "";
    0.upto(word.size-1) do |i|
      sw[i] = a[i]
    end
    if (ag[sw] == nil) then ag[sw] = [word] else ag[sw] << word end   
  end
  result = []
  ag.each_key do |key| result << ag[key] end 
  result
end