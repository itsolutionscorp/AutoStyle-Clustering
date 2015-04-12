class Hamming
  def compute sequence1, sequence2
    hamming = 0
    (0...sequence1.length).each do |position|
      hamming += 1 if sequence2[position] unless sequence2[position] == sequence1[position]
    end
    hamming
  end
end
