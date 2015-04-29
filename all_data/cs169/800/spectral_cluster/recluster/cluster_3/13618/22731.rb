

def combine_anagrams(words)
  output = []
  sorted_input = words.map { |word| word.downcase.chars.sort.join }
  #p sorted_input
  while words.size != 0
    aim = [words.shift]
    sorted_input.shift
    #puts "aim is now #{aim}."
    sorted_input.each_with_index do |word, index|
      #puts "#{index} : #{word}."
      if word == aim[0].downcase.chars.sort.join
        #puts "Bingo! #{index} : #{word}."
        aim << words.delete_at(index)
        sorted_input.delete_at(index)
        #puts "The word list:"
        #p words
        #puts "\nThe sorted list:"
        #p sorted_input
        #puts "The temp list is now #{aim}."
      end
    end
    #puts "The words list is #{words}"
    output << aim
  end
  output
end


    