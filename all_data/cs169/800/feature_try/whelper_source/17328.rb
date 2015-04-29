def sort
  self.split(//).sort.join
end

def combine_anagrams(words)
  output_list = []
  words.each_with_index do |word, i|
    temp_output_list = []
    temp_output_list.push(word)
    (i + 1).upto((words.length - 1)) do |j|
      if words[j].is_a?(String) and (word.downcase.sort == words[j].downcase.sort) then
        temp_output_list.push(words[j])
        words.delete(words[j])
      end
    end
    output_list.push(temp_output_list)
  end
  return output_list
end

