def combine_anagrams(words)
  groups = Array.new
  words_srt = Array.new(words)
  words_srt.collect! do |x|
    x = x.downcase
    x = x.chars.sort.join
  end
  words_srt_unq = words_srt.uniq
  i = 0
  while (i < words_srt_unq.length) do
    tmp_group = Array.new
    j = 0
    while (j < words_srt.length) do
      tmp_group = (tmp_group + [words[j]]) if (words_srt[j] == words_srt_unq[i])
      j = (j + 1)
    end
    groups[i] = tmp_group
    i = (i + 1)
  end
  return groups
end