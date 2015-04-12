def compute(uno, dos)
        cont=0
        for i in 0..uno.length-1
            if dos[i] 
                if uno[i] != dos[i]
                    cont +=1
                end
            end
        end
        return cont
    end