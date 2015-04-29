
   def count_letters(str)
      rex = Regexp.new(/(\w)/i)
      result = Hash.new
      str.scan(/(\w)/i).each {
        |s|
        letter = s[0].downcase()
        if result.has_key?(letter)
          result[letter] = result[letter]+1;
        else
          result[letter] = 1;
        end 
      }
      return result;
   end
  #['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
  def combine_anagrams(words)
    letter_counts = Hash.new
    words.each { |word|  
      #value contains count of the letters for each word
      value = count_letters(word)
      if letter_counts.has_key?(value)
        letter_counts[value].push(word)
      else
        letter_counts[value]=[word]
      end
      }
    result = []
    letter_counts.each_value() { |value| result.push(value)}
    return result
  end
  
#combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','Scar', 'creams', 'sCream'])

#mock_obj  = PartThree.new()

#result = mock_obj.combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','Scar', 'creams', 'sCream'])

#puts "length : #{result.length()}"

#result.each() {|elt| puts "---";puts elt}