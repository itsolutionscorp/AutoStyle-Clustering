def anagramize(string)
  string.downcase.split(//).sort.join.squeeze
end

def combine_anagrams(words)
  groups = Array.new
  words.each do |word|
    flag = false
    flag1 = false
    groups.each do |group|
      if (anagramize(word) == anagramize(group[0])) then
        group.each do |ele|
          if (word == ele) then
            p("#{word} in #{group}")
            flag = true
            break
          end
        end
        if (not flag) then
          (group << word)
          flag1 = true
          break
        end
      end
    end
    (groups << [word]) if (not flag1) and (not flag)
  end
  groups
end

