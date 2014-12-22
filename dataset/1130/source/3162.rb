# input:
#['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
  i = []
  words.each do |w|
    z = words.select {|v| v.scan(/./).sort == w.scan(/./).sort }
    if not i.include? z
      i << z
    end
  end
  i
end
