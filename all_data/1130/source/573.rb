#!/usr/bin/ruby

class Array
    def to_s
        self.each_with_index do |element,index|
            printf ",%s", element unless index == 0
            printf "%s", element if index == 0
        end

        puts
    end
end

class String
    def anagram_key
        return self.downcase.chars.to_a.sort
    end

    def anagram?(other)
        return self.anagram_key == other.anagram_key if other != nil
        return false if other == nil
    end
end

def combine_anagrams(words)
    anagram_map = Hash.new
    anagram_array = Array.new

    words.each do |word| 
        anagram_map[word.anagram_key] = anagram_map[word.anagram_key].push word if anagram_map.has_key? word.anagram_key
        anagram_map[word.anagram_key] = [word] unless anagram_map.has_key? word.anagram_key
    end

    anagram_map.each_key { |key| anagram_array.push anagram_map[key] }

    return anagram_array
end


words = ['a', 'a', 'b', 'b', 'c', 'd']
#words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream', 'scream']
anagram_array = combine_anagrams(words)

anagram_array.each { |element| element.to_s }
