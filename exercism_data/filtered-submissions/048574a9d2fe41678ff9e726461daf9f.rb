class Hamming
  def compute(strand_one, strand_two)
    strand_one.chars[0..strand_two.length - 1].zip(strand_two.chars).count { |each_set| each_set[0] != each_set[1]}
  end
end
