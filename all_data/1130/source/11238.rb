def combine_anagrams(words)
  groups = []
#  remains = Array.new(words)
  words.each do |w1|
#    group = [w1]
    groups << words.select { |w2| w2 if order_chars(w1) == order_chars(w2) }
#    remains.select do |w2|
#      if order_chars(w1) == order_chars(w2) and w1 != w2
#        group << w2 
#      end
#    end
#    groups << group
  end
  groups.uniq
end

def order_chars(word)
  word.downcase.chars.sort.join
end
