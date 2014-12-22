def combine_anagrams(words)
  out = Array.new
  words.each do |word|
    dex = out.index { |ana| wordsort(word).eql?(wordsort(ana[0])) }
    if dex.nil? then
      ana = Array.new
      (out << ana)
      (ana << word)
    else
      (out[dex] << word)
    end
  end
  return out
end

def wordsort(word)
  word.downcase.split("").sort
end

