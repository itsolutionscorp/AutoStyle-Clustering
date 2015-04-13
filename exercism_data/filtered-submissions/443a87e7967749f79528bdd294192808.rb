def compute(strand1,strand2)

        @diff_count = 0


        if strand1.length === strand2.length

            [strand1.chars,strand2.chars].transpose.each do |char1,char2|

                if char1 != char2

                    @diff_count += 1
                end
            end

        end


        @diff_count

    end