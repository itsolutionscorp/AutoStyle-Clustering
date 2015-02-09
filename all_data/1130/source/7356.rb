#input = ['Cars', 'for', 'potatoes', 'racs', 'four', 'scar', 'creams', 'scream']
def combine_anagrams(words)
  groups = Array.new
  words.each do |w|
    word_chars = w.downcase.chars.sort.join;
    result = Array.new
    groups.each do |g|
      first_word_chars = g[0].downcase.chars.sort.join;
      if (word_chars == first_word_chars)
        result << g
      end
    end
    if (result.length == 0)
      groups << [ w ]
    else
      result.each { |r| r << w }
    end
  end
  groups
end

