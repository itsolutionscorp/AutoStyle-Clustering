def add_to_group(groups, w)
  groups.each do |gr|
    if (gr.length > 0) and (is_anagram?(gr[0], w) and (not gr.include?(w))) then
      gr = gr.push(w)
      return
    end
  end
  groups = groups.push([w])
end

def combine_anagrams(words)
  groups = []
  words.each { |w| add_to_group(groups, w) }
  return groups
end

