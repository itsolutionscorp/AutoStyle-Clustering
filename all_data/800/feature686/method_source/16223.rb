def combine_anagrams(words)
  ahash = Hash.new
  words.each do |w|
    sw = w.split(/\s*/).sort.to_s
    wg = ahash.fetch(sw, nil)
    if (wg == nil) then
      wg = Array.new
      (wg << w)
      ahash.store(sw, wg)
    else
      (wg << w)
    end
  end
  anagrams = Array.new
  ahash.each_value { |wg| (anagrams << wg) }
  anagrams
end