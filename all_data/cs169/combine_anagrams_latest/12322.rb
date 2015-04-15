# HW 1: Ruby calisthenics

# Part 3: anagrams
# combine_anagrams

def combine_anagrams(words)
  uniq_anas = words.map{|word| word.downcase.chars.sort.join}.uniq
  uniq_anas.map do |ua|
    words.select {|w| w.downcase.chars.sort.join == ua}
  end
end
