class Hamming
  def compute strands_one, strands_two
    strands_one, strands_two = strands_two, strands_one if strands_two.size < strands_one.size
    strands_one, strands_two = strands_one.chars, strands_two.chars
    strands_one.size.times.inject(0) { |sum, i| sum + (strands_one[i] != strands_two[i] ? 1 : 0) }
  end
end
