def combine_anagrams(words)
  arrayResult = []
  arrayGroupIndex = []
  arrayGroup = []
  wordsSort = []
  wordsSort = sort_words(words)
  wordsMod = wordsSort.uniq
  wordsMod.each_index do |eltUniq|
    arrayGroupIndex = []
    arrayGroup = []
    wordsSort.each_index do |elt|
      (arrayGroupIndex << elt) if wordsMod[eltUniq].eql?(wordsSort[elt])
    end
    arrayGroupIndex.each_index do |elt|
      eltResult = arrayGroupIndex[elt]
      arrayGroup[elt] = words[eltResult]
    end
    (arrayResult << arrayGroup)
  end
  arrayResult
end

def sort_words(words)
  wordsSort = []
  words.each_index do |word|
    wordsSort[word] = words[word].downcase.scan(/./).sort.join
  end
  wordsSort
end

