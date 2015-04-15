#SaaS_HW1_P3

def combine_anagrams(words)
  comb = []
  words.each do |ana|
    if comb.size == 0
      comb << [ana]
    else
      in_list = false
      comb.each do |in_ana|
        if in_ana[0].downcase.chars.sort.join == ana.downcase.chars.sort.join
          comb[comb.index(in_ana)] << ana
          in_list = true
        end
      end
      if ! in_list
        comb << [ana]      
      end
    end
  end
  return comb
end
