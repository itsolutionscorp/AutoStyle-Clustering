class Hamming
  def compute strand_one, strand_two
    difference = 0
    strand_one.chars.each_with_index do |_char, _index|
      break if strand_two[_index].nil?
      difference += 1 if !!(_char != strand_two[_index])
    end
    difference
  end
end
