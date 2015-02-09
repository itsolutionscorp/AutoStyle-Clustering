def print_ln(line)
    print line.to_s + "\n";
end


def combine_anagrams(words)
    anagrams = [];
    anagramsInserted = {};
    words.each do |word|
        wordDown = word.downcase
        wordSortted = wordDown.chars.sort_by(&:downcase).join
        #print_ln wordSortted;
        if anagramsInserted.has_key?(wordSortted) then
            position = anagramsInserted[wordSortted];
            anagrams[position].push(word);
            #print_ln anagrams[position];
        else
            newArray = [];
            newArray.push(word);
            #print_ln newArray.to_s;
            position = anagrams.length;
            anagramsInserted[wordSortted] = position;
            anagrams.push(newArray);
        end
    end
    anagrams;
end

words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream', 'hello', 'Hello', 'A', 'a']

#print_ln combine_anagrams(words);