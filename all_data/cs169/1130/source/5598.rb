# input:
# ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],
# ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter

def combine_anagrams(words)
  #  <YOUR CODE HERE>
  if (words == [])
    return [] # handle empty list
  end
  result = []
  # initialize the result by putting the first word in the first sub-array.
  result.push([words[0]])
  words = words[1..-1] # keep 2nd el to end, but don't mutate the
                       # parameter
  for w in words
    # Quite inefficient, as it standardizes words many times. A
    # dictionary or prefix tree should be more effective on large data
    # sets.
    # Search the groups for a match, and if not, add (push) a group
    std_w = standardize(w)
    found = false # flag
    for group in result
      # puts 'group (before):', group.inspect
      if standardize(group[0]) == std_w
        group.push(w); found = true
      end
      # puts 'group (after):', group.inspect, 'result:', result.inspect
    end
    result.push([w]) unless found
  end
  return result
end

def standardize(word)
  # convert word to an uppercase alphasorted string to identify
  # anagrams

  # Implementation: can't sort a string, so convert to an array of
  # chars, sort, then join into a string again
  return word.upcase.chars.to_a.sort.join('')
end

def demo
  words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
  combine_anagrams(words)
end

def test
  raise StandardError unless combine_anagrams([]) == []
  puts 'Empty list test passes'
end
