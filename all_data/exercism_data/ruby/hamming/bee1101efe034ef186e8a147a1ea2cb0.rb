class Hamming
  def self.compute(strand1, strand2)
    distance = 0
    strand1, strand2 = strand1.split(""), strand2.split("")

    strand1.zip(strand2).each do |s1, s2|
      next if s1.nil? || s2.nil?
      distance +=1 if s1 != s2
    end

    distance
  end
end
