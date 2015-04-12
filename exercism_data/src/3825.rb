class Hamming
    def compute (first, second)
        count = 0
        firstArr = first.split("")
        secondArr = second.split("")

        firstArr.each_with_index { |c, i| 
            count += 1 if secondArr[i] != c
        }

        return count
    end
end
