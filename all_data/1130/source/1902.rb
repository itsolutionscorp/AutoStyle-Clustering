class EmptyArrayError < StandardError ; end

def combine_anagrams(words)
  raise EmptyArrayError if words.empty?
  hash = {}
  words.each do |word|
    formatted_word = word.downcase.split(//).sort
    if hash[:"#{formatted_word}"].nil?
      hash[:"#{formatted_word}"] = [word]
    else
      hash[:"#{formatted_word}"] << word
    end
  end
  hash.collect{|k, v| v }
end