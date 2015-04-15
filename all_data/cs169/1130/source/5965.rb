# input: 
#input = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], 
#               ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
  output = Array.new
  words.each{ |word|
    if output.empty?
      output += [[word]]
    else
      added_new = false
      output.each{ |list|
        if list[0].sort.downcase == word.sort.downcase
          list << word
          added_new = true
        end
      }
      output += [[word]] unless added_new
    end
  }
  output
end

class String
  def sort
    self.split('').sort{ |x,y| x.downcase <=> y.downcase }.join
  end
end

#print combine_anagrams(input)
