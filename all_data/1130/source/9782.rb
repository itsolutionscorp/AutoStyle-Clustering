#! /usr/bin/env ruby

def strToArr s
  a = []
  s.each_char do |e|
    a << e
  end
  a
end

def arrToStr a
  s = ""
  a.each do |e|
    s << e
  end
  s
end

def combine_anagrams list

  sorter = Hash.new{|h, k| h[k] = []}
  list.each do |l|
    sorter[arrToStr(strToArr(l.downcase).sort!)] << l
  end
  # a = Array.new 
  sorter.values
  #  sorter.values.each{|e| a << e.inspect}
  # a
end

def test_0
  $\ = "\n"
  $, = ', '

  anagrams = combine_anagrams ['caRs', 'for', 'potatoes', 'racs', 'four',
                                'scar', 'creams', 'scream']

  print anagrams
  if anagrams.size != 5
    print 'test_0 bad, wrong size'
  else
    print 'ok'
  end

  expected = [
              ["four"],
              ["potatoes"],
              ["caRs", "racs", "scar"],
              ["for"],
              ["creams", "scream"],
             ]

  anagrams.each do |a|
    if not expected.include? a
      print 'bad anagram list'
    else
      print 'ok'
    end
  end
  
end

def test_1
  anagrams= combine_anagrams []
  if anagrams != []
      puts 'test_1 bad', anagrams.inspect
  else
      puts 'ok'
  end
end

def test_all
  test_0
  test_1
end

# test_all

