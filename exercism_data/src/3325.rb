class Hamming

  def compute (strand1, strand2)
    (strand1.chars).zip(strand2.chars).inject(0) do |count, (base1, base2)|
      base1 == base2 ? count : count + 1
    end 
  end

end
