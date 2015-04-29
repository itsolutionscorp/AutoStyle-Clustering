def check_groups(groups, w)
        cur_group = Array.new
        i = 0
    #    puts groups.inspect
                groups.each do |g|
   #             puts g.inspect
                        if g.size > 0 and g[0].downcase.split(//).sort == w.downcase.split(//).sort
                                g << w
                                i = i+1
                        end

                end
                if i == 0
                        cur_group << w
                        groups << cur_group
     #                   puts groups.inspect
                end
return groups
end

def combine_anagrams(words)
        groups = Array.new

        p = 0
        words.each do |w|
                i = 0
                groups = check_groups(groups, w)
                p = p + 1
 #               puts 'p=' + p.to_s
  #              puts 'i=' + i.to_s
                #puts groups.inspect
        end
        return groups
end

#puts combine_anagrams(['Cars', 'for', 'potatoes', 'racs', 'four','scar', 'crEAms', 'scream']).inspect
