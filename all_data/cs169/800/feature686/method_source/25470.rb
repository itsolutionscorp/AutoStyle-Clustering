def combine_anagrams(words)
  words2 = words.dup
  words2.map do |elt|
    d = elt.to_a
    c = elt.chars.to_a.sort.to_s.downcase
    words2.each do |elt2|
      if (elt2.chars.to_a.sort.to_s.downcase == c) and (elt != elt2) then
        (d << elt2)
        words2.delete(elt2)
      end
    end
    d
  end
end