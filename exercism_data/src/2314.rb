class Hamming
  def compute(base, mutant)
    base.chars.each_with_index.count do |base_char, index|
      mutant[index] != base_char
    end
  end
end
