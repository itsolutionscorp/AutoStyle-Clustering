# HW1.3 Anagrams

def combine_anagrams(words)
  anagrams=words.group_by{|w| w.upcase.split(/\s*/).sort.join}.values
end

words = %w{cars for potatoes racs four scar creams scream}

puts combine_anagrams(words).inspect
