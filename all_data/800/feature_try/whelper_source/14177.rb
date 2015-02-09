def combine_anagrams(words)
  h = Hash.new
  words.each do |word|
    tempword = word.downcase
    tempword = sortletters(tempword)
    if h.key?(tempword) then
      (h[tempword] << word)
    else
      h[tempword] = Array.new
      (h[tempword] << word)
    end
  end
  grouparray = Array.new
  h.keys.each { |group| (grouparray << h[group]) }
  return grouparray
end

def sortletters(word)
  charray = Array.new
  word.each_char { |cha| (charray << cha) }
  charray = charray.sort
  newstr = String.new
  charray.each { |cha| newstr = (newstr + cha) }
  return newstr
end

