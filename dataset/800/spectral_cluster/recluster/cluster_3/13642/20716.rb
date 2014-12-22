
def combine_anagrams(words)
  hwds = {}
  words.each do |word|
    awds = []
    word.each_char {|ch| awds << ch}
    awds.sort!
    hwds[awds] ||= []
    hwds[awds] << word
  end
  hwds.values
end

puts combine_anagrams ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']

