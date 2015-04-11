class Hamming
  def self.compute(first, second)
    first.chars.each_with_index.inject(0){|diff, (item, index)| diff += (item <=> second[index]).abs}
  end
end
