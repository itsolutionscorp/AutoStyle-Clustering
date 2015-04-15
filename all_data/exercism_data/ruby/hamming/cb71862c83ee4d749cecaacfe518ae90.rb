class Hamming
  class << self
    def compute(strand1, strand2)
      diff(strand1[0...strand2.length].chars, strand2[0...strand1.length].chars)
    end

    def diff(strand1, strand2)
      strand1.zip(strand2).delete_if {|a,b| a == b}.length
    end
  end
end
