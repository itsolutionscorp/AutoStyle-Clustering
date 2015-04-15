def compute(first, second)

    i = 0


    shorter, longer = [first, second].sort


    shorter.length.times { |index| i += 1 unless shorter[index] == longer[index] }
    i
  end