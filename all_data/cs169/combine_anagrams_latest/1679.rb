def combine_anagrams (words)
  anagrams = Array.new

  words.each do |word|
    group = get_group(anagrams, word)
    group << word
  end

  return anagrams;
end



def get_group(anagrams, word)
  anagrams.each do |group|
    word_compare_str = build_compare_str(word);
    group_compare_str = build_compare_str(group[0]);

    if (word_compare_str == group_compare_str)
      return group;
    end
  end

  # If we've gotten this far, there's no group that suits our word.
  # We'll need to create a new on.

  new_group = Array.new;
  anagrams << new_group;
  return(new_group);
end



def build_compare_str(word)
  word.downcase.chars.sort.join
end