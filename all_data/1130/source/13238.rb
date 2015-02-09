# Input array of strings
# ex. ['aaa','aaa','rac','car']
# Output array of string arrays grouped by anagramness
# ex. [['aaa','aaa'],['rac','car']]

def combine_anagrams(words)
  groups = Array.new
# for each word
  words.each do |word|
    word_added = false
#   check each group of anagrams
    groups.each do |group|
#     if word.ordered matches any in list.ordered
      if word.downcase.split(//).sort.join == group[0].downcase.split(//).sort.join
#       add orignal word to group
        group << word
        word_added = true
      end
    end
#   Have we added the word yet?
    unless word_added
#     Create new group
      groups << [word]
    end
  end
  groups
end

