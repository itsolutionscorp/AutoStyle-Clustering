
def combine_anagrams(words)
  first_arr = words
  output_arr = Array.new

  words.each  do |wordOne|
    newArr = Array.new
    newArr << wordOne
    words.each do |wordTwo|

      found = false

      if output_arr.include?(wordOne) or output_arr.include?(wordTwo)
        break
      end

        if wordOne != wordTwo and wordOne.chars.sort.join == wordTwo.chars.sort.join

           newArr << wordTwo

          #first_arr.delete(wordOne)
          #first_arr.delete(wordTwo)

        end
      output_arr << newArr

    end


end

  output_arr.map! {|sub_arr| sub_arr.sort! }

  output_arr.uniq!
  output_arr
end
#combine_anagrams(['a'])
#puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).to_s
#combine_anagrams(['asdf', 'fdsa','zxc','qwe','ewq','a'])