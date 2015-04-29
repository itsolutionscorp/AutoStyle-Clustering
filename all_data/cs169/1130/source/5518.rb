#!/usr/bin/env ruby

# # #
# anagrams
#


def combine_anagrams(words)
  groups = Hash.new{|h,k| h[k] = []}

  words.each {|word|
    code = word.downcase.split('').sort.join
    groups[code] << word
  }

  return groups.map {|arr| arr[1]}
end

######################################################################

if $0 == __FILE__

  anagram_tests = [
    [
      # input
      ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'],
      # result
      [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
    ],

    [
      # input
      ['CARS', 'for', 'potatoes', 'racs', 'four', 'SCAR', 'creaMs', 'scream'],
      # result
      [["CARS", "racs", "SCAR"], ["four"], ["for"], ["potatoes"], ["creaMs", "scream"]]
    ]

  ]

  anagram_tests.each {|words, anagrams|
    result = combine_anagrams(words)
    # the comparison is unreliable. inner arrays are not sorted
    if result.sort == anagrams.sort
      puts "SUCCESS"
    else
      puts "FAILED"
      puts "Extected: #{anagrams.inspect}"
      puts "Actual: #{result.inspect}"
    end
    #
  }
end
