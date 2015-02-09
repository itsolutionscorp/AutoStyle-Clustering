#input: 
#['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], 
#["creams", "scream"]]

def combine_anagrams(words)
    ar=[]
    words.each do |str|
        gotarray=false
        if ar.empty?
            ar=ar.push([str])
            gotarray=true
        else
            ar.each do |anag|
                #print " arr :"
                #print anag
                #print " / "
                #print str+" "+anag.at(0)+" "
                #print str.downcase.chars.sort.class
                #print anag.at(0).downcase.chars.sort.class
                #print str.downcase.chars.sort.join
                #print " "
                #print anag.at(0).downcase.chars.sort.join
                #print "\n"
                if str.downcase.chars.sort.join==anag.at(0).downcase.chars.sort.join
                    anag=anag.push(str)
                    #print " arrstr :"
                    #print anag
                    #print " "
                    #print str+" "+anag.at(0)
                    #print "\n"
                    gotarray=true
                end
            end
        end
        if not gotarray
            ar=ar.push([str])
        end
    end
    #print "ar : \n"
    #ar.each do |arr|
    #    print arr
    #    print "\n"
    #end
    #print "\n"
    return ar
end

