#!/usr/bin/ruby

def combine_anagrams(words)
  anagrams_array = Array.new

  anagrams = Hash.new(0)
  
  words.each do |word|
    if anagrams[word.downcase()] == 1
      # already in anagrams list, no need to check it again
      anagrams_array.each do |existing_array|
        if existing_array.include?(word)
          existing_array << word
        end
      end
      next
    end

    current_array = Array.new
    current_array << word

    # compare with each words
    words.each do |word_to_compare|

      if word.downcase() != word_to_compare.downcase() # and anagrams[word_to_compare.downcase()] != 1
        # source the word's characters alphabetically
        if word.downcase().chars.sort.join == word_to_compare.downcase().chars.sort.join
          puts "#{word} and #{word_to_compare} are anagrams"
          #if anagrams[word.downcase()] != 1
            # this is a new anagrams
            anagrams[word.downcase()] = 1
          #end

          #if anagrams[word_to_compare.downcase()] != 1
            # this is a new anagrams
            anagrams[word_to_compare.downcase()] = 1
            current_array << word_to_compare.downcase()
          #end 
        end
      end

    end # end of word_to_comare loop
    anagrams_array << current_array
  end

  return anagrams_array
end


if __FILE__ == $0
  words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream', 'cars']
  puts combine_anagrams(words).inspect
  # => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],
  words = ["creams", "scream"]
  puts combine_anagrams(words).inspect
  words = ['a','b','c']
  puts combine_anagrams(words).inspect
end

