def combine_anagrams(words)
  groups = []
  words.each do |w|
    letters = w.downcase.each_char.sort
    group = groups.detect { |g| g[0].downcase.each_char.sort == letters }
    if group
      group << w
    else
      groups << [w]
    end
  end
  groups
end
