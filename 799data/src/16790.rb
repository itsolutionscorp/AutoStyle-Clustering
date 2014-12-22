def combine_anagrams( words )
  anagram_map = {}
  words.each do |word|
    (anagram_map[ word.downcase.split( // ).sort.join ] ||= []) << word
  end
  anagram_map.values
end

=begin

load "part3.rb"

words = %w{ cars for potatoes racs four scar creams scream }

combine_anagrams words

=end
