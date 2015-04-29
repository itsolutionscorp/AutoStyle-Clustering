def combine_anagrams(words)
    groups = Hash.new
    words.each do |x|
        key = x.downcase.chars.sort.join
        if (groups.has_key?(key))
            groups[key] = groups[key] + [x]
        else
            groups[key] = [x]
        end
   end
   return groups.values
end