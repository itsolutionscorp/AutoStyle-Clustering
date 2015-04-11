class Hamming
  def self.compute(first_strand, second_strand)
    #default answer
    answer = 0

    #only compute difference if both strands have the same length
    unless first_strand.length != second_strand.length
      (0..first_strand.length).each do |i|
        answer = answer+1 if first_strand[i] != second_strand[i]
      end
    end

    answer
  end
end
