def combine_anagrams(words)
  anagrams = []
  words.each do |w|
    if anagrams.length == 0
      anagrams << [ w ]
    else
      wCanonical = w.downcase.split('').sort().join()
      gotit = false
      anagrams.each do |a|
        if a[0].downcase.split('').sort().join() == wCanonical
          a << w
          gotit = true
          break
        end
      end
      if not gotit
        anagrams << [w]
      end
    end
  end
  return anagrams
end
