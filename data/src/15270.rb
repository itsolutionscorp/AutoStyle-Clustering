def combine_anagrams(words)
    result = Array.new()
    words.each do |item|
        words -= [item]
        if result.flatten.include?(item) == false then
            mid_result = Array.new()
            mid_result += [item]
            words.each do |entry|
                if entry.split(//).sort == item.split(//).sort then
                    mid_result += [entry]
                end
            end
            result << mid_result
        end
    end
    return result
end