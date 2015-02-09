def combine_anagrams(words)
    groups = Hash.new
    words.each do |original_word|
        word = original_word.downcase
        if groups[sort_letters(word)] == nil
            groups[sort_letters(word)] = [original_word]
        else
            groups[sort_letters(word)].push(original_word)
        end
    end
    final = Array.new
    groups.each do |item|
        final.push(item[1])
    end
    final
end

def sort_letters(word)
    return word.split(//).sort.join('')
end

def print_array(array)
    puts "["
    array.each do |item|
        if item.is_a?(Array)
            print_array(item)
        else
            puts "#{item},"
        end
    end
    puts "]"
end

a1 = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
print_array a1
puts "a1:"
print_array(combine_anagrams(a1))
a2 = ['helLo', 'for', 'potatoes', 'HEllo', 'four','helLO', 'creams', 'scream']
puts "a2:"
print_array(combine_anagrams(a2))
a3 = ['A', 'for', 'potatoes', 'a', 'four', 'creams', 'scream', 'a']
puts "a3:"
print_array(combine_anagrams(a3))

