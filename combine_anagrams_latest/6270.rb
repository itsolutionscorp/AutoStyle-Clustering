def combine_anagrams(words)
  sorted_words=[]
  words.each do |word|
    sorted_words << word.chars.sort_by(&:downcase).join   
  end

  result=[]
  for i in 0..sorted_words.length-1 do
    word=sorted_words[i]  
      added=0
      result.each do |item|
        puts "analyzing:"
        puts item
         if word.downcase==item[0].downcase.chars.sort_by(&:downcase).join
           item << words[i]
            added=1
         end
      end
      if added==0
        result << Array[words[i]]
      end
  end
  puts result
  return result
end
