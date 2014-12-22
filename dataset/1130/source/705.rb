#! /usr/bin/env ruby

def combine_anagrams(words)
  dic = Hash.new { |hash, key| hash[key] = [] }
  words.each do |word|
    dic[word.downcase.chars.sort.join] << word
  end
  return [*(dic.each_value)]
end
