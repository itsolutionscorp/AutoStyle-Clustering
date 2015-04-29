





def combine_anagrams(words)
#
  print "input:\n["
  i = 0
  words.each { |w|  
     print w
     i += 1
     w = w.downcase
     if words[i] != nil
        print " , " 
     end
  }
  print "]\n"

  h = Hash.new
  words.each { |w|
     print w, " "
     idx = w.chars.sort.join
     idx = idx.downcase
     if h[idx] == nil
        h[idx] = Array.new
     end
     h[idx].push(w)
     h[idx].sort
  }

  ret = Array.new
  h.each { |idx, v|
      print "index:", idx,"\n"  
      group = Array.new
      v.each { |val|
         print val, " "
         group.push( val )
      } 
      print "\n"     
      ret.push(group)
    } 
  
  i = 0

  print "output:\n["
  ret.each { |r|
     print "["
     y = 0
     r.each { |v|
        print v
        y += 1
        if r[y] != nil
           print " , "
        end 
     } 
     print "]"
     i += 1
     if ret[i] != nil
        print " , "
     end
  }  
  print "]\n"

  return ret
end






if __FILE__ == $0
  l=  ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
  r = combine_anagrams(l)
  print r, "\n"
  l = ['A', 'a']
  r = combine_anagrams(l)
  print r, "\n"
  l = ["hello", "HeLLo"]
  r = combine_anagrams(l)
  print r, "\n"
end

