class Hamming
  def compute(*strings)
    sequences = []
    # split strings into single-char arrays and append to array
    strings.each do |string|
      sequences << string.split('')
    end

    # initialize distance counter to zero
    distance = 0

    # zip all the sequences together
    sequences[0].zip(*sequences[1..-1]).each do |*chars|
      # increment distance if the chars aren't the same
      distance += 1 unless chars.flatten.uniq.length == 1
    end

    distance
  end
end
