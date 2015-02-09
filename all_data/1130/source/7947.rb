def combine_anagrams(words)
    groups = []
    words.each do |w|
        pushed = false
        groups.each do |g|
            if (w.downcase.split("").sort.to_s == g[0])
                g.push(w)
                pushed = true
                break
            end
        end
        if pushed == false
            groups.push([w.downcase.split("").sort.to_s, w])
        end
    end
    groups.each do |g|
        g.delete_at(0)
    end
    groups
end
