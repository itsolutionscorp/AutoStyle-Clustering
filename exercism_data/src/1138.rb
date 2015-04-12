class Hamming

    def compute(strand1,strand2)
        # variables
        @diff_count = 0 #count differences between strands

        # only compare if strands are same length
        if strand1.length === strand2.length

            [strand1.chars,strand2.chars].transpose.each do |char1,char2|
                #compare characters from strand1 to strand2
                if char1 != char2
                    #increase difference counter when characters aren't equal
                    @diff_count += 1
                end
            end

        end

        #return diff_count
        @diff_count

    end

end
