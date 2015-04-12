def compute(strand_a, strand_b)

        return 0 if strand_a.eql? strand_b

        min = strand_a.length < strand_b.length ? strand_a : strand_b

        count = 0
        for i in 0...min.length
            if strand_a[i] != strand_b[i]
                count += 1
            end
        end

        return count

    end