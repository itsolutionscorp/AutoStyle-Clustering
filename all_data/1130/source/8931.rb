def combine_anagrams(p)
    q = []
    p.each{|x| q.push(x.chars.sort.join)}
    r = []
    r = q.zip(p)
    r.sort!
    a = r.transpose[0]
    b = r.transpose[1]
    output = []
    while(!b.empty?)
        temp = a[0]
        output.push(b.slice!(0..a.rindex(temp)))
        a.slice!(0..a.rindex(temp))
    end
    return output
end
