def combine_anagrams(words)
  new = []
  final = []
  i = 0
  temp = words.clone
  until (i == temp.length) do
    (temp.each do |a|
      if (new.length == 0) and (not final.flatten.include?(a)) then
        (new << a.clone)
      else
        if (a.downcase.chars.sort.join == new[0].downcase.chars.sort.join) then
          (new << a.clone)
        end
      end
    end
    (final << new.clone)
    new.clear
    i = (i + 1))
  end
  return final
end

