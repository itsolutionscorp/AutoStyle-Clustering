def combine_anagrams(words)
  $groups = []
  words.each do |word|
    $classified = false
    $groups.each do |group|
      if(word.downcase.chars.sort.join == group[0].downcase.chars.sort.join) then
        group.push(word)
        $classified = true
        break
      end
    end
    if (!$classified) then
      $groups.push([word])
    end    
  end
  return $groups
end
