# Rodrigo Machado - machador@gmail.com
def combine_anagrams(words)
  ret = Array.new()
  if words != nil
    words.each do |word|
      # code here
      newGroup = Array.new()
      groupFlag = true
      ret.each do |group|
        if group[0].downcase.split(//).sort.join == word.downcase.split(//).sort.join
          group.push word
          groupFlag = false
        end
      end
      if groupFlag
        newGroup.push word
        ret.push newGroup
      end   
    end
  end
  return ret
end


