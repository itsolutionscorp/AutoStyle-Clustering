
  def combine_anagrams(words)

    anagrams=words.group_by() do |lexis|

      lexis.downcase.chars.sort

    end.values

    #print(anagrams);
    return anagrams;
  end


wordlist1=['c','c'];
wordlist2=['cars', 'for', 'potatoes', 'Racs', 'four','scar', 'creams', 'scream'];

#puts(wordlist);

print combine_anagrams(wordlist1);