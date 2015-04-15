def compute(strand1, strand2)
        distance = 0


        shortest = [strand1.length, strand2.length].min - 1

        pairs = strand1.split(//)[0..shortest].zip(
            strand2.split(//)[0..shortest]
        )

        pairs.each { |pair| distance += 1 unless pair[0] == pair[1] }

        distance
    end