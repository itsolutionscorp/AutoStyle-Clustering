class String
  def sort
    self.chars.sort.join
  end

  def anagrams?(word)
    if (word.downcase.sort == self.downcase.sort)
      puts "#{word.downcase.sort} == #{self.downcase.sort}"
      return true
    end
    puts "#{word.downcase.sort} != #{self.downcase.sort}"
    false
  end
end

=begin
 We should receive a list of words, to be grouped in anagrams groups
 Case is not relevant, but should be reflected on the output with the same Case as in the input 
=end
def combine_anagrams(list_of_words)
  result = []
  list_of_words.each { | word |
      found = false 
      for added_words in result
        if word.anagrams?(added_words[0])
          added_words << word
          puts "==>#{added_words}"
          found = true
          break       
        end  
      end
      #Add the new anagram group list
      if !found then result << [word] end
    }
  return result
end
