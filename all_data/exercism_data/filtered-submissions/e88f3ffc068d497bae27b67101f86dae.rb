def compute(strand1, strand2)

    if strand1 == strand2
      return 0

    else

      s1 = strand1.split(%r{\s*})
      s2 = strand2.split(%r{\s*})

      hammingCount = 0



      unless s1.count > s2.count
        zippedStrands = s1.zip(s2)

        zippedStrands.each do |x,y|
          unless x == y


            hammingCount += 1
          end
        end
        return hammingCount



      else
        zippedStrands = s2.zip(s1)

        zippedStrands.each do |x,y|
          unless x == y


            hammingCount += 1
          end
        end
        return hammingCount

      end
    end
  end