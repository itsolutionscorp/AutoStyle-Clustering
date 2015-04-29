def combine_anagrams(array)
  hash = {}
  array.each do |word|
    key = sort_string(word.downcase)
    hash[key] ||= []
    hash[key] << word
  end

  hash.values
end

def sort_string(string)
  string.split('').sort.join
end
