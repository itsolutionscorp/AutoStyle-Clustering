class Hamming

  def compute(first_strand, second_strand)
    #set the counter variable to zero
    counter = 0
    #find the shortest of the two strands                                                           # => 0
    min = [first_strand, second_strand].min_by { |strand| strand.length }
    #use the shortest of the two as the max number of iterations through the index positions
    number = min.length - 1

    #iterate through each index position of the strands, evaluating whether they match
    0.upto(number) do |x|
      if first_strand[x] != second_strand[x]
        counter +=1
      end
    end

    counter

  end
end
