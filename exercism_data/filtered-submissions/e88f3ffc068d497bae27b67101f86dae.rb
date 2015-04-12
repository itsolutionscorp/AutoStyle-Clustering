def compute(strand1, strand2)
    #If strands are equal, return a hamming distance of zero
    if strand1 == strand2
      return 0

    else
      #Convert the strands to arrays so we can compare them
      s1 = strand1.split(%r{\s*})
      s2 = strand2.split(%r{\s*})
      #Initialize the hamming distance as zero
      hammingCount = 0

      #Drop the excess elements in strand1 if it is
      #greater than strand2 and zip them together into pairs.
      unless s1.count > s2.count
        zippedStrands = s1.zip(s2)
        #Loop thru the zipped array to compare each pair.
        zippedStrands.each do |x,y|
          unless x == y
            #Add a hamming distance of one to hammingCount
            #unless the elements are the same.
            hammingCount += 1
          end
        end
        return hammingCount

      #Drop the excess elements in strand2 because it is
      #greater than strand1 and zip them together.
      else
        zippedStrands = s2.zip(s1)
        #Loop thru the zipped array to compare each pair.
        zippedStrands.each do |x,y|
          unless x == y
            #Add a hamming distance of one to hammingCount
            #unless the elements are the same.
            hammingCount += 1
          end
        end
        return hammingCount

      end
    end
  end