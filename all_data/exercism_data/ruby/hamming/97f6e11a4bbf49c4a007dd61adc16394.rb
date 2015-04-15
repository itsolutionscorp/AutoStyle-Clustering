class Hamming

  def self.compute(strand1, strand2)
    new(strand1,strand2).differences
  end

  attr_reader :strand1, :strand2
  def initialize(strand1, strand2)
    @strand1 = strand1
    @strand2 = strand2
  end

  def differences
    str1.each_with_index.count do |c,i|
      mutation?(c, str2[i])
    end
  end

  private

  def sequence_length
   [@strand1.length,@strand2.length].min - 1
  end

  def str1
    strand1[0..sequence_length].chars
  end

  def str2
    strand2[0..sequence_length]
  end

  def mutation?(s1, s2)
    s1 != s2
  end
  
end
