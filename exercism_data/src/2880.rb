class Hamming 
  def compute(first_strand, second_strand)
    count=0
    first=first_strand.split ""
    second=second_strand.split ""
    first.each_with_index { |item,index|
      if second[index] != first[index]
        count = count + 1
      end
    }
    count
  end
end
