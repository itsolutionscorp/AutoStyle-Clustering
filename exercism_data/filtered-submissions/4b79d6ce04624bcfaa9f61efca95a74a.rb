# hamming.rb
# Hamming distance between nucleotide strands

class Hamming
  def compute(s1, s2)
    e1 = s1.chars()
    e2 = s2.chars()
    
    distance = 0
    loop do
      distance+=1 if (e1.next != e2.next)
    end
    distance
  end
end
