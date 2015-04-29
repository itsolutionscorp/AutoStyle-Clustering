class Hamming
  def self.compute(a, b)
    a[0...b.size].split('').reject.with_index { |c, i| c == b[i] }.size
  end
end

# Hamming.compute('A', 'A')
# Hamming.compute('A','G')
# Hamming.compute('AG','CT')
# Hamming.compute('AT','CT')
# Hamming.compute('GGACG', 'GGTCG')
# Hamming.compute('AGAGACTTA', 'AAA')
# Hamming.compute('AGG', 'AAAACTGACCCACCCCAGG')
# Hamming.compute('GATACA', 'GCATAA')
# Hamming.compute('GGACGGATTCTG', 'AGGACGGATTCT')
