# Part 3 anagrams

# input : an array of strings
# output: an array containing arrays of groups

# step 1 print input
words =['caRs','For','poTatoes','racS','Four','scar','cReams','Scream']


def combine_anagrams( inp ) 
  # a dict to hold groups
  grps = {}

  inp.each do |wrd|
      
      k = wrd.downcase.split(%r{}).sort!

      if grps.has_key?(k) then grps[k] << wrd else grps[k]=[wrd] end
  end
  answer = grps.values()
  return answer
#answer.each { |l| puts l,l.length }
end

print combine_anagrams words
