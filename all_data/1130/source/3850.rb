def combine_anagrams(words)
  sets = []
  until words.empty?
    base_word = words[0]
    set = [base_word]
    words[1..(words.length - 1)].each do |word|
      if base_word.downcase.chars.sort.join == word.downcase.chars.sort.join
        set = set + [word]
      end
    end
    sets = sets + [set];
    words = words - set;
  end
  return sets
end
