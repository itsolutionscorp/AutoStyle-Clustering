def combine_anagrams(words)
    o = []
    words.each do |wi|
        t = []
        words.each do |wj|
            # split(//).sort ? char vector from w*
            if(wi.downcase.split(//).sort == wj.downcase.split(//).sort) 
                t.push(wj)
            end
        end     
        o.push(t)
    end
    # uniq ? new_ary :: Returns a new array by removing duplicate values in self.
    return o.uniq 
end