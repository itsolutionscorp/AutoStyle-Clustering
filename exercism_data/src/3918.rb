def compute (first, second)
        count = 0

        for i in (0...first.length) 
            count += 1 if first[i] != second[i]
        end

        return count
    end