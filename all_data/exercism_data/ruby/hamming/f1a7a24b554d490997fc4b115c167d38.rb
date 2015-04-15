# centzon400
# 2014-10-02
# 2.1.3p242
# calculate hamming distance


class Hamming
  
  def self.compute (a_strand , b_strand)
    base_strand = a_strand.each_char.to_a
    comp_strand = b_strand.each_char.to_a
    hamming_distance = 0

    strand_dif = base_strand.length <=> comp_strand.length

    case strand_dif
      when 1 #1st strand larger
        overflow = base_strand.length - comp_strand.length
        base_strand.pop(overflow)
      when -1 #2nd strand larger
        overflow = comp_strand.length - base_strand.length
        comp_strand.pop(overflow) 
    end
    
    base_strand.each_with_index do |base, idx|
      if base != comp_strand[idx]
         hamming_distance +=1
       else hamming_distance
       end
     end
     
     return hamming_distance
     
  end #compute

end #Hamming

a_strand = "GAGCCTACTAACGGGATAAAAAA"
b_strand = "CATCGTAATGACGGCCTAAAAA"
p Hamming.compute(a_strand , b_strand)
