def anagrams?(w1, w2)
  w1.downcase.chars.sort.join == w2.downcase.chars.sort.join
end

def combine_anagrams(words)

  words.sort!
  words_ref = words.clone
  groups = []

  while words_ref.length != 0  
    count = 0
    anagrama = []
    words_ref.each do |w| #recorro grupo de referencias
      if anagrams?(words_ref[0], w)
        anagrama << w
      end
    end
    while count != words_ref.length
      if anagrams?(words_ref[count], anagrama[0])
        words_ref.delete_at(count)
      else 
        count = count + 1
      end
    end
    groups << anagrama  
  end
  return groups
end
