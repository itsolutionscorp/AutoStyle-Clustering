def combine_anagrams(words)
  if(words.length == 0)
    return []
  end
  sort = words[0].downcase.split(//).sort.join
  for i in 1 .. words.length-1 do
    sort = [sort].concat([words[i].downcase.split(//).sort.join])
  end
  sort = sort.flatten
  group = []
  for j in 0 .. words.length-1 do
    if(words[j].length > 0)
      sub_group = [words[j]]
      for i in j+1 .. sort.length-1 do
        if(sort[j] == sort[i])
          sub_group = [sub_group].concat([words[i]])
          words[i] = ""
          sort[i] = ""
        end
      end
      sub_group = sub_group.flatten
      group = group.concat([sub_group])
      words[j] = ""
    end
  end
  return group
end
