

def combine_anagrams(words)
  sorted=Hash.new()
  words.each do |word|
    sortedWord=word.downcase.split(//).sort
    if sorted[sortedWord]
      sorted[sortedWord]+=[word]
    else
      sorted[sortedWord]=[word]
    end
  end
    return sorted.values
end


#testing



