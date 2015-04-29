def compute(dna1, dna2)
        result = 0

        dna1.chars.zip(dna2.chars) do |char1, char2|
            if char2.nil?
                break
            elsif char1 != char2
                result += 1
            end
        end

        result
    end