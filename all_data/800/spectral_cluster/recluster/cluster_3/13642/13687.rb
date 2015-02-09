#3)

#input
#['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#['creams', 'scream']

def combine_anagrams(words)
  new = []
  final = []
  i = 0
  temp = words.clone
     until i == temp.length
      temp.each do |a| 
        if new.length == 0 && !(final.flatten.include? a)
          new << a.clone
        elsif a.downcase.chars.sort.join == new[0].downcase.chars.sort.join
          new << a.clone
        end
      end
      final << new.clone
      new.clear
      #print new
      #print "\n"
      #print final
      #print "\n"
      i = i + 1
    end
  return final
end
