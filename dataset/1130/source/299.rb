def combine_anagrams(words)
  result = []
  words.each { |word|
    added = false
    result.each { |array|
      if array[0].downcase.chars.sort.join.eql? word.downcase.chars.sort.join
        array << word
        added = true
        break
      end
    }
    result << [word] unless added
  }
  result
end
