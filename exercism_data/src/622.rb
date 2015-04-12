def compute(strand1, strand2)

    min_length = [strand1.length,strand2.length].min
    counter = 0
    (0...min_length).each do |n|
      counter += 1 if strand1.chars[n] != strand2.chars[n]
    end
    counter
  end

end