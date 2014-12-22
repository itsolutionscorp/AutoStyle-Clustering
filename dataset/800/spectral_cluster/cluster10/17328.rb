class String
  def sort
    self.split(//).sort.join
  end
end


def combine_anagrams(words)

  output_list = []
  
  words.each_with_index do |word, i|
    temp_output_list = []
    temp_output_list.push(word)
    
    (i+1).upto(words.length-1) { |j|
      if words[j].is_a?(String) && word.downcase.sort == words[j].downcase.sort
        temp_output_list.push(words[j])
        words.delete(words[j])
      end
    }
    output_list.push(temp_output_list)
  end
  return output_list
end

#anagram = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#puts combine_anagrams(anagram)
