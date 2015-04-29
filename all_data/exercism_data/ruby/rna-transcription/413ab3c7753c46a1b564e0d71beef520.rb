class Complement
  def self.of_dna(strand)
    strand.gsub(/\w/) do |w|
      raise ArgumentError if w == 'U'
      r = 'C' if w == 'G'
      r = 'G' if w == 'C'
      r = 'A' if w == 'T'
      r = 'U' if w == 'A'
      w = r
    end
  end

  def self.of_rna(strand)
    strand.gsub(/\w/) do |w|
      raise ArgumentError if w == 'T'
      d = 'G' if w == 'C'
      d = 'C' if w == 'G'
      d = 'T' if w == 'A'
      d = 'A' if w == 'U'
      w = d
    end
  end
end
