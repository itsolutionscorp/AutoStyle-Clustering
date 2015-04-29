# words = ['cars', 'for', 'potatoes', 'Racs', 'four','scAr', 'creams', 'scream']

def combine_anagrams(words)
  i = 0
  anagrams = []
  (0..words.size-1).each do |i|
    sub_array =[]
    words.each do |w|
      w1_sorted = w.chars.sort_by(&:downcase).join.downcase
      w2_sorted = words[i].to_s.chars.sort_by(&:downcase).join.downcase
      if w1_sorted == w2_sorted then
        sub_array << w if !anagrams.flatten.any? {|word| word == w }
      end
    end
    anagrams << sub_array
    i += 1
  end
  anagrams.each.reject { |elmt| elmt.empty? }
end

# p combine_anagrams(words)