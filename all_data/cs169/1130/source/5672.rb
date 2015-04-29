
def combine_anagrams(words)
    groups = {}
    words.each do |word|
        key = word.downcase.split(//).sort.join
        groups[key] = Array.new if !groups.has_key?(key)
        groups[key].push(word)
    end
    return groups.values
end


if __FILE__ == $0
    input = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
    # => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
    puts combine_anagrams(input).inspect
    puts combine_anagrams([]).inspect
    puts combine_anagrams(['asd', 'sda', 'dsa']).inspect
end
