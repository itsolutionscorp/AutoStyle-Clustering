#       Author : Felipe campos Vega
# Country/City : Mexico, Querétaro
#         Date : jun 6 2012
#         email: inverlynx@gmail.com

# 3.a

def combine_anagrams(words)
  anagramGroups={}
  words.each { |word|    
    key=word.downcase.split('').sort.join
    anagramGroups[key] ||= []
    anagramGroups[key] << word
  }
  return anagramGroups.values
end 
