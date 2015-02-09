def combine_anagrams(words)
    word_pairs = []
    word_pairs_sorted = []
    words.each do |word|
      word_sorted = word.downcase.split('').sort.join
      word_pairs += [[word_sorted, word]]
    end
    word_pairs_sorted = word_pairs.sort { |a, b|  a[0] <=> b[0] }
    #print "\n"
    #print word_pairs_sorted

    anagrams = []

    if word_pairs_sorted.size > 0
      last_pair = word_pairs_sorted[0]
      work_list = [word_pairs_sorted[0][1]]
      #print word_pairs_sorted.size - 1
      print "\n"
      for i in  1..(word_pairs_sorted.size - 1) do
         if word_pairs_sorted[i][0] > last_pair[0]
           anagrams = anagrams += [work_list]
           last_pair = word_pairs_sorted[i]
           work_list = [word_pairs_sorted[i][1]]
         elsif  word_pairs_sorted[i][0] == last_pair[0]
           last_pair = word_pairs_sorted[i]
           work_list += [last_pair[1]]
        else
        end
      end
      anagrams = anagrams += [work_list]
    end
    anagrams
end

input = ['Cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#input = []

print combine_anagrams(input)