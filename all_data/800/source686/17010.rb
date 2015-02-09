#! /usr/bin/ruby


def combine_anagrams(words)
  result = []
  words.each do |word|
    found = false
    result.each do |search|
      if search[0].downcase.split("").sort == word.downcase.split("").sort
        search.push(word)
        found = true
        break
      end
    end
    if !found
      result.push([ word ])
    end
  end

  return result
end
