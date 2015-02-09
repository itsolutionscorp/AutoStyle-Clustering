def combine_anagrams(words)

  result = []
  words.each do |w|

    added = false
    if result.empty?
      result << [w]
      added = true
    else  
      result.each do |r|
        unless r.empty?
          if r[0].chars.sort(&:casecmp).join.casecmp(w.chars.sort(&:casecmp).join) == 0
            r << w
            added = true          
          end
        end
    end 
    unless added
      result << [w]
    end
  end
  end

  return result
end
