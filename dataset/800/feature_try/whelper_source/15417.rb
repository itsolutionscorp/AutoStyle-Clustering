def sort
  sorted_chars = to_s.chars.sort
  sorted_word = ""
  sorted_chars.each { |ch| sorted_word = (sorted_word + ch) }
  return sorted_word
end

def combine_anagrams(words)
  return words.group_by { |w| w.downcase.sort }.values
end

