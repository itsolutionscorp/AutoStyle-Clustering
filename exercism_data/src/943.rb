def compute(strand1,strand2)
    differences = 0

    if ( strand1.length == strand2.length ) then
      strand1.length.times do |index|
        if ( strand1[index] != strand2[index] ) then
          differences += 1
        end
      end
    end

    differences
  end