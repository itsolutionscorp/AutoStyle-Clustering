#!/usr/bin/env ruby

class String
  def sort
    self.split(//).sort.join
  end
end

def combine_anagrams(words)
  combining = {}
  words.each do
    |value|
    key = value.downcase.sort
    if combining.has_key?(key)
      combining[key] = combining[key] << value
    else
      combining[key] = [] << value
    end
  end
  combining.values
end

#words = ['cars', 'for', 'potatoes', 'racs', 'four', 'scar', 'creams', 'scream']
#combine_anagrams(words)

#words = ['Cars', 'for', 'potatoes', 'racs', 'four', 'scar', 'creams', 'screaM']
#combine_anagrams(words)