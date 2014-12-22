################################################################################
# 3. anagrams

def combine_anagrams(words)
  words.sort do |a,b|
    a.downcase.scan(/./).sort.join <=> b.downcase.scan(/./).sort.join
  end.group_by {|x| x.downcase.scan(/./).sort.join}.values
end

#raise "Assert" unless combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).sort == [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]].sort
#raise "Assert" unless combine_anagrams(['cARs', 'for', 'potatoes', 'raCS', 'four','sCar', 'creamS', 'scream']).sort == [["cARs", "raCS", "sCar"], ["four"], ["for"], ["potatoes"], ["creamS", "scream"]].sort

