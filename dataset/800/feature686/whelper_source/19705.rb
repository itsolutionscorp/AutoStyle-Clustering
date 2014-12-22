def combine_anagrams(*words)
  words = words.flatten
  hash_table = Hash.new
  words.each do |x|
    x.split.each do |y|
      key = y.downcase.chars.sort.join
      if hash_table.has_key?(key) then
        if ((not hash_table.values_at(key).include?(y)) or ((key.length == 1) and (y.upcase != y))) then
          hash_table[key] += [y]
        end
      else
        hash_table[key] = [y]
      end
    end
  end
  return hash_table.values
end

