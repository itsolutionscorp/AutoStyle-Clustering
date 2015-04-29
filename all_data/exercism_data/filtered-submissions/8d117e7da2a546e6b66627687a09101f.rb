def compute(seq_a, seq_b)
    hamm = 0



    seq_a.each_char.zip(seq_b.each_char).each do |a, b|
      break if a.nil? || b.nil?
      hamm += 1 if a != b
    end
    hamm
  end