# Part 3
def combine_anagrams(words)
    groups = Array.new()
    words.each{|x|
        a = x.downcase.split(//).sort
        key = -1
        groups.each_index{|y|
            if groups[y][0].downcase.split(//).sort.eql?(a)
                key = y
                break
            end
        }
        if key == -1
            groups.push([x])
        else
            groups[key].push(x)
        end
    }

    return groups
end
