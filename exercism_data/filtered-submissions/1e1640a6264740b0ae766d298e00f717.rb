class Hamming

  def compute(sequence, mutation)
    sequence = sequence.to_s.chars
    mutation = mutation.to_s.chars

    length = [sequence.length, mutation.length].min

    distance = 0
    (0..(length-1)).each do |i|
      distance +=1 if sequence[i] != mutation[i]
    end

    distance
  end

end
