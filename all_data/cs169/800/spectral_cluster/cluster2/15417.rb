class String
  def sort
    sorted_chars = to_s.chars.sort
    sorted_word=""
    sorted_chars.each do |ch|
      sorted_word += ch
    end
    return sorted_word
  end
end

def combine_anagrams(words)
  return words.group_by{ |w| w.downcase.sort }.values
end
