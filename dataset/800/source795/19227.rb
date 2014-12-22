# Homework 1
# Part 3
# Anagrams

def combine_anagrams(words)
 answer = words.group_by{ |word| word.chars.sort}.values;
 return answer;
end
