def compute(strand1, strand2)
        distance = 0
        # compute the shortest length between the two, minus 1 to use to slice
        # the array
        shortest = [strand1.length, strand2.length].min - 1

        pairs = strand1.split(//)[0..shortest].zip(
            strand2.split(//)[0..shortest]
        )

        pairs.count { |pair| pair[0] != pair[1] }
    end