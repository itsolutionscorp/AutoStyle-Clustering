class Hamming

  def self.compute firstStrand, secondStrand
    size = [firstStrand.size, secondStrand.size].min
    distance = 0
    (0...size).each {|i|
      distance = distance + 1 if firstStrand[i] != secondStrand[i]
    }
    distance
  end

end

puts Hamming.compute('AA', 'ABA')
