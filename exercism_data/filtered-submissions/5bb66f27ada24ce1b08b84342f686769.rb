class Hamming
  def compute(firstDNA, secondDNA)
    shorterDNALength = (firstDNA.length < secondDNA.length) ? firstDNA.length : secondDNA.length
    nucleotidesRange = 0..(shorterDNALength - 1)
    hammingDistance = 0
    for singleNucleotide in nucleotidesRange
      hammingDistance += 1 if firstDNA[singleNucleotide] != secondDNA[singleNucleotide]
    end
    hammingDistance
  end
end
