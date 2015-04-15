def compute(arg1, arg2)
    # Counts the number of positions where arg1 and arg2 differ.
    # Assumes they are of equal length.
    ham = 0
    arg1.length.times do |index|
      if arg1[index] != arg2[index]
        ham += 1
      end
    end
    return ham
  end