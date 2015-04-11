class Hamming

  def self.compute(strand1, strand2)

    bases1 = strand1.split("")
    bases2 = strand2.split("")

    differences = 0

    bases1.each_with_index do |letter, ind|
      next if bases2[ind].nil?
      differences += 1 if letter != bases2[ind]
    end

    differences
  end

end
