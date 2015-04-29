def combine_anagrams(words)
  answers = Array.new
  answers << Array.new
  
  answer = words.group_by {|word| word.chars.sort }
  return answer.values
end
