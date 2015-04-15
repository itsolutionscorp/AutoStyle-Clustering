class Complement
  def self.method_missing name, strand
    order = name.to_s == 'of_dna' ? [0, 1] : [1, 0]
    pairs = Hash[[['G', 'C'], ['C', 'G'], ['T', 'A'], ['A', 'U']].map{ |v| [v[order[0]], v[order[1]]] }]
    strand.gsub(/./) { |char| pairs[char] }
  end
end
