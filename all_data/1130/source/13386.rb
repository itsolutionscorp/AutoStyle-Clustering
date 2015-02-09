# Part 3
def combine_anagrams(words)
  h = Hash.new
  words.each do |str|
    sorted = str.downcase.chars.sort
    if (h[sorted]== nil)
       h[sorted] = Array.new
    end
    arr = h[sorted]
    arr[arr.length] = str
    h[sorted] = arr
    arr = nil
  end
 
  h.values
end
