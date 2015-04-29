#!/usr/bin/ruby

def combine_anagrams(words)
  
  h=Hash.new

  words.each do |word|
    tempword=word.downcase
    tempword=sortletters(tempword)
    
    if h.key? tempword
      h[tempword] << word
    else
      h[tempword] = Array.new
      h[tempword] << word
    end
  end
  
  grouparray=Array.new
  (h.keys).each do |group|
    grouparray << h[group]
  end
  return grouparray
end


def sortletters(word)
  charray=Array.new
  word.each_char do |cha|
    charray << cha
  end
  charray=charray.sort
  newstr=String.new
  
  charray.each do |cha|
    newstr+=cha
  end  
  
  return newstr
end

def testanagrams
  testarray =combine_anagrams(["dad","add","sad","fad","ADD","CAD","mad","lad","dam","das"])
  
  puts testarray.inspect
end
