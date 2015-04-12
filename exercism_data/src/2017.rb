class Hamming

  def compute(first, second)
    # start a count
    i = 0
    # set variables for short and long based on lengths
    # of first and second
    shorter, longer = [first, second].sort
    # compare each of shorter's letters with respective letter of
    # longer and increment i if they are different
    shorter.length.times { |index| i += 1 unless shorter[index] == longer[index] }
    i
  end

end
