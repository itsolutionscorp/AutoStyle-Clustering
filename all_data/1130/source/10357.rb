def combine_anagrams(words)
  if (words == nil)
    return []
  end

  if (words.length == 1)
    return [words]
  end

  sortArray = words.collect { |item| item.downcase.scan(/./).sort.join }
  uniqArray = sortArray.uniq
  result = Array.new
  words.each do |item|
    uniqArray.each_with_index do |uniqitem, index|
      if ( item.downcase.scan(/./).sort.join == uniqitem )
        if ( result[index] == nil )
          result[index] = [item]
        else
          result[index] << item
        end
      end
    end
  end
  return result
end

=begin
test1 = ['cars', 'for', 'potatoes', 'racs', 'four', 'scar', 'creams', 'scream']
p combine_anagrams(test1)
p combine_anagrams([])
p combine_anagrams(nil)
test2 = ["Rats", "carS", "STAR", "rATs", "racs", "acs", "for", "ACT"]
p combine_anagrams(test2)
=end

