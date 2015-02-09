def combine_anagrams(words)
  comb = []
  words.each do |ana|
    if (comb.size == 0) then
      (comb << [ana])
    else
      in_list = false
      comb.each do |in_ana|
        if (in_ana[0].downcase.chars.sort.join == ana.downcase.chars.sort.join) then
          (comb[comb.index(in_ana)] << ana)
          in_list = true
        end
      end
      (comb << [ana]) if (not in_list)
    end
  end
  return comb
end

