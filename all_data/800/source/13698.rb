
def word_sort(word)
    return word.downcase.split(//).sort.join
end

def combine_anagrams(words)
    # if words == []
    #     return []
    # end
    
    # output = []
    # words.each_with_index do |w, index|
    #     w2 = word_sort(w)
    #     #puts w2
    #     if output == []
    #         #puts "make list"
    #         list_w = [w]
    #         output << list_w
    #         #puts "now output is"
    #         #puts output
    #     else
    #         output.each_with_index do |out, index|
    #             #puts out
    #             if w2 == word_sort(out[0])
    #                 #puts "found"
    #                 out << w
    #                 break
    #             else
    #                 #puts "append"
    #                 output << [w]
    #                 break
    #             end
    #         end  
    #     end
    # end
    # return output
    output = Hash.new

    words.each_with_index do |w, index|
        w2 = word_sort(w)

        if output.has_key? w2
            output[w2] << w
        else
            output[w2] = [w]
        end
    end
    return output.values

end

input = ['cars', 'for', 'potatoes', 'racs', 'four','Scar', 'creams', 
    'scream']
o = combine_anagrams(input)
puts o.inspect
input = []
puts combine_anagrams(input).inspect
input = ['C']
puts combine_anagrams(input).inspect
