def compute(strand1, strand2)
    strand1.chars.zip(strand2.chars).inject(0) do |sum, (base1, base2)|
      sum + (base2.nil? || base1 == base2 ? 0 : 1)
    end
  end
end