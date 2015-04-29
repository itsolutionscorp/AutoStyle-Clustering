def compute(first, second)
    shorter = first.length >= second.length ? second : first
    hamming_counter = 0
    (0..shorter.length - 1).each do |index|
       hamming_counter = hamming_counter + 1 if first[index] != second[index]
    end
    hamming_counter
  end