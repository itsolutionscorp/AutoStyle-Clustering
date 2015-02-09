
def combine_anagrams(words)
  sorted = []
  words.each { |string| sorted << string.chars.sort { |a, b| a.casecmp(b) } .join.downcase }
  uniq_sorted = sorted.uniq
  anagrams = []
  uniq_sorted.each do |string1| 
    angrm = []
    sorted.each_index do |index|
      if string1 == sorted[index]
         angrm << words[index]
      end
    end
    anagrams << angrm
  end
  anagrams
end

# input = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# p combine_anagrams(input)
