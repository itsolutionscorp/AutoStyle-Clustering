def combine_anagrams(words)
  ar = []
  words.each do |str|
    gotarray = false
    if ar.empty? then
      ar = ar.push([str])
      gotarray = true
    else
      ar.each do |anag|
        if (str.downcase.chars.sort.join == anag.at(0).downcase.chars.sort.join) then
          anag = anag.push(str)
          gotarray = true
        end
      end
    end
    ar = ar.push([str]) if (not gotarray)
  end
  return ar
end

