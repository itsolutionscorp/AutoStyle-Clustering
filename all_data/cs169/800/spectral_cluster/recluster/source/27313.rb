def combine_anagrams(words)
  wsorted=[]
  words.each { |w| 
       wsorted << w.downcase.split(//).sort.join }
  gpd=[]

  wsorted.each { 
            |w| 
            grouped=[]            
            i=0
            wsorted.each {|c| 
                    if (w==c) 
                        grouped << words[i]
                    end
                    i=i+1
                    }
                    gpd << grouped 
                    }
  return gpd.uniq
end

