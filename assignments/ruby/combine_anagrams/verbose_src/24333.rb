def combine_anagrams(words)
 [] if words.empty?
 words.group_by { |element| element.downcase.chars.sort }.values
end


input=["cars", "for", "potatoes", "races", "four", "scar", "creams", "scream"]
puts combine_anagrams(input).inspect


