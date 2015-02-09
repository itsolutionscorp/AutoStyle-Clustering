def combine_anagrams(words)
  ana_list = []
  sorted_list = []
  words.each do |word|
    word = word.downcase
    temp_list = word.scan(/./)
    temp_list.sort!
    sorted_list.push(temp_list)
  end

  for i in 0..(sorted_list.size - 1)
    exist = false
    ana_list.each do |item|
      if item.include?(words[i])
        exist = true
        break
      end
    end

    if not exist
      temp_list = []
      for j in i..(sorted_list.size - 1)
        if sorted_list[i] == sorted_list[j]
          temp_list.push(words[j])
        end
      end
      ana_list.push(temp_list)
    end
  end

  return ana_list
end

=begin
words = ["cars", "for" ,"potatoes", "racs",
         "four", "scar", "creams", "scream"]

p combine_anagrams(words)
=end
