def is_anagram?(w1,w2)

    if not w1 or not w2
        return false
    end
    
    w1.downcase!
    w2.downcase!
    
    if w1.length != w2.length
        return false
    end
    
    w1_arr = w1.split("")
    w2_arr = w2.split("")
    
    w1_arr.map do |entry|
        begin
            w2_arr.delete_at( w2_arr.index(entry) )
        rescue
            return false
        end
    end
    
    if 0==w2_arr.length
        return true # that is anagram
    end
    
    return false
end


def add_to_group(groups,w)
    
    #print "push group w='%s'" % w
    
    groups.each do |gr|
        if gr.length > 0 and is_anagram?(gr[0],w) and ( not gr.include?(w))
            gr = gr.push( w )
            return
        end
    end
    
    #print "push group w='%s'" % w
    groups = groups.push( [w] )
    #print "groups = %s\n" % groups   
    #groups.push(["sss"],["sss",["aaaa"]],["sss"])
end

def combine_anagrams(words)
    groups = []
    words.each do |w|
        add_to_group(groups,w)
    end
    return groups
end

#puts is_anagram?("cars", "racs")
#puts is_anagram?("racs", "scar")
#puts is_anagram?("four", "for")
#puts is_anagram?("potatoes", "potatoeS")
#puts is_anagram?("potatoes", "potatoeg")

puts 'combine_anagrams_1'
input  = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
result = [ ["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"] ]
print result
#puts "\n---\n"
ggg = combine_anagrams(input)
print ggg
puts result == combine_anagrams(input)

puts 'combine_anagrams_2'
input  = []
result = []
puts result == combine_anagrams(input)
