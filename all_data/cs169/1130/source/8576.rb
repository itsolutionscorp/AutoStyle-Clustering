def combine_anagrams(words)
  grp = Hash.new()
  if words == nil
    return
  end
  words.map do |w|
    (key = w.downcase.chars.sort.join
    if grp.has_key?(key)
      grp[key] << w
    else
      grp[key] = [w]
    end) unless w == nil
  end
  grp.values.to_s
end

#puts combine_anagrams nil
#
#puts combine_anagrams []
#
#puts combine_anagrams [nil]
#
#puts combine_anagrams ['c']
#
#puts combine_anagrams ['c', 'a']
#
#puts combine_anagrams ['wer','t', 'yuiu']
#
#puts combine_anagrams ['wer','t', 'wert']
#
#puts combine_anagrams ['U ', 'Cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#
#puts combine_anagrams ['c', 'c']
#
#puts combine_anagrams ['Cars', 'for', 'potatoes', 'Cars', 'racs', 'racs', 'four','scar', 'creams', 'scream']
#
#puts combine_anagrams ['C', 'c']
