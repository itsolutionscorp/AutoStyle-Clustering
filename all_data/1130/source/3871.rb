#!/usr/bin/env ruby

def combine_anagrams(words)
   anagram_groups= Hash.new(0)
   groups = []
   group_id = 0

   words.each do |word|
      anagram = word.downcase.chars.sort(&:casecmp).join
         if anagram_groups.has_key?(anagram)
            group_id=anagram_groups[anagram]
         else
            group_id=groups.length
            anagram_groups[anagram] = group_id
            groups[group_id] = []

         end

         groups[group_id].push(word)

   end
   return groups.sort
end

#test = ['HeLLo','hello','potatoes','racs','four','scar','scream','creams','A','a']
#
#print combine_anagrams(test)
