#!/usr/bin/ruby -w

def combine_anagrams(words)
    result = []
    sorted = words.collect {|x| x.downcase.chars.sort.join}
    words.each do |word|
        insert = false
        result.each do |rlist|
            if rlist.collect {|x| x.downcase.chars.sort.join}.include? word.downcase.chars.sort.join
                rlist << word
                insert = true
                break
            end
        end
        if not insert == true : result << [word]  end

    end
    return result
end


input = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
combine_anagrams(input)

