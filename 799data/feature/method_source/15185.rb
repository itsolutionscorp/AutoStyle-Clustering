def combine_anagrams(words)
  words.sort!
  words_ref = words.clone
  groups = []
  while (words_ref.length != 0) do
    count = 0
    anagrama = []
    words_ref.each { |w| (anagrama << w) if anagrams?(words_ref[0], w) }
    while (count != words_ref.length) do
      if anagrams?(words_ref[count], anagrama[0]) then
        words_ref.delete_at(count)
      else
        count = (count + 1)
      end
    end
    (groups << anagrama)
  end
  return groups
end