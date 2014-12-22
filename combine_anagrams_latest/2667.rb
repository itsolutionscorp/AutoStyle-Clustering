def combine_anagrams(words)
  group = Hash.new
  words.each{ |item|
    sorted_string = item.downcase.split('').sort.join
    if ( group.has_key?(sorted_string) )
      group.fetch(sorted_string).push(item)
    else
      temp = Array.new
      temp.push( item )
      group.store( sorted_string, temp )
    end
  }

  return group.values
end