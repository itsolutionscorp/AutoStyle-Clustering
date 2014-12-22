def combine
  self.char_counts = get_char_counts
  self.combined = words.inject(Array.new) do |memo, k|
    v = char_counts[k]
    if memo.empty? then
      (memo << [k])
    else
      added_to = memo.inject(nil) do |found, arr|
        if compare_words(v, char_counts[arr.first]) then
          (arr << k)
          found = k
          break found
        end
      end
      (memo << [k]) unless added_to
    end
    memo
  end
end

def combine_anagrams(words)
  AnagramStats.new(words).combine
end

