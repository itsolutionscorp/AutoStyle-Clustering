def combine_anagrams(words)
  words.group_by { |term| term.downcase.unpack("c*").sort.pack("c*") }.map { |k, vs| vs }
end
