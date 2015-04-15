def combine_anagrams(words)
  results = Array.new
  
  words.each {|e| 
    found = 0;
    results.each_index {|i| 
      if(results[i].first.downcase.split(//).sort.join == e.downcase.split(//).sort.join)
        results[i].push(e)
        found = 1;
      end
    }

    if(found == 0)
      results.push([e])
    end

  }
  return results

end


# input: 
#puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).inspect
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter
