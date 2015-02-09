def combine_anagrams(words)
  ana = []
  words.each do |i|
    cana = []
    words.each do |j|
      if (j.split(//).uniq.downcase.sort.join == i.split(//).uniq.downcase.sort.join) then
        cana = (cana + [j])
      end
    end
    ana = (ana + cana)
  end
  return ana
end

