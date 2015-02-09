# hw1pt3.rb
# Created by Aaron Lindsey
# May 30, 2012

def combine_anagrams(words)
  result = Array.new
  words.each do |i|
    anagrams = Array.new
    sorted = i.downcase.chars.sort.join
    words.each do |j|
      if j.downcase.chars.sort.join == sorted
        anagrams << j
      end
    end
    result << anagrams
  end
  return result.uniq
end
