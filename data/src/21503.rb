def combine_anagrams(words)
  word_pairs = []
  word_pairs_sorted = []
  words.each do |word|
    word_sorted = word.downcase.split("").sort.join
    word_pairs = (word_pairs + [[word_sorted, word]])
  end
  word_pairs_sorted = word_pairs.sort { |a, b| (a[0] <=> b[0]) }
  anagrams = []
  if (word_pairs_sorted.size > 0) then
    last_pair = word_pairs_sorted[0]
    work_list = [word_pairs_sorted[0][1]]
    print("\n")
    for i in (1..(word_pairs_sorted.size - 1)) do
      if (word_pairs_sorted[i][0] > last_pair[0]) then
        anagrams = anagrams = (anagrams + [work_list])
        last_pair = word_pairs_sorted[i]
        work_list = [word_pairs_sorted[i][1]]
      else
        if (word_pairs_sorted[i][0] == last_pair[0]) then
          last_pair = word_pairs_sorted[i]
          work_list = (work_list + [last_pair[1]])
        end
      end
    end
    anagrams = anagrams = (anagrams + [work_list])
  end
  anagrams
end