def combine_anagrams(words)
  combine_anagrams_aux words
end

def combine_anagrams_aux(words, acc = [])
  if words.empty?
    acc
  elsif words.length == 1
    acc << words
  else
    assert = word_chars(words[0])

    anagrams, rest = words.
      slice(1..words.length-1).
      partition { |word| assert == word_chars(word) }

    acc << (anagrams << words[0])
    combine_anagrams_aux rest, acc
  end
end

def word_chars word
  word.downcase.chars.to_a.sort
end