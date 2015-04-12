class Hamming
  def compute(seq_a, seq_b)
    shorter, longer = [seq_a, seq_b].sort_by(&:length)
    differences = 0
    shorter.chars.each_with_index do |character, index|
      differences += 1 if character != longer[index]
    end
    differences
  end
end
