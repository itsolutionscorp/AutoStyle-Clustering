# Part 3: Anagrams

def combine_anagrams(words)
  words.group_by {|w| w.split('').sort}.values
end
