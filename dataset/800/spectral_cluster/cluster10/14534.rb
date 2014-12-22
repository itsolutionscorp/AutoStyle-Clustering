# Form of parameter "words": array of strings
# strategy: initially output = []
#
# Sample input parameter:
# words = ["cars", "for", "potatoes", "racs", "four","scar", "creams", "scream"]
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
#
# Some more anagrams to test:
# words = "parroted","Tokyo","ester","steer","skate","teardrop","earth","silt","pas","hater","pertness","prorated","snug","heart","sorter","list","seam","Spa","roster","mesa","reset","sap","resort","sung","same","presents","slit","stake","hatreds","serpents","gnus","dearths","terse","threads","predator","steak","trees","asp","teaks","hardest","guns","trashed","takes"
#
# =================================================================================================================
# LOGIC
# For each different length word, create a sublist of words having that length
# word_bins = [["for"], ["cars", "racs", "four", "scar"], ["creams", "scream"]]
#
# Double up each word (zip the list against itself) so you can have a sorted and unsorted version that are joined at the hip.
# word_bins2 = [[["for","for"]], [["cars","cars"], ["racs","racs"], ["four","four"], ["scar","scar"]], [["creams","creams"], ["scream","scream"]]]
#
# Lowercase and sort the first element of each innermost list:
# [[["for","for"]], [["acrs","cars"], ["acrs","racs"], ["fuor","four"], ["acrs","scar"]], [["acemrs","creams"], ["acemrs","scream"]]]
#
# Within each sublist (all words have the same length in each sublist), 
# create a list of the second strings for each unique first string
#
# =================================================================================================================

def combine_anagrams(words)
  @results = []
  word_bins = words.group_by { |word| word.length }
  word_bins.values.map { |sublist| 
    sublist = sublist.zip(sublist)
    sublist.map { |wordpairs| wordpairs[0] = wordpairs[0].downcase.chars.sort.join }
    # collect unique keys for this sublist:
    keys = sublist.collect { |list| list[0] }.uniq
    # print "keys: ", keys, "\n"
    keys.each do |key| 
      anagrams = sublist.collect { |list| list[0] == key ? list[1] : nil }
      anagrams.compact!
      @results << anagrams
      # print "anagrams= ", anagrams, "\n"
      end
    # print sublist.length, ": ", sublist, "\n\n" 
  }
  # print "final results: ", @results, "\n"
  @results
end

