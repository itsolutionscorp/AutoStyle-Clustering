class Hamming
  
  def compute( strand_1, strand_2 )

    length = [strand_1.size, strand_2.size].min
    
    (0...length).count { |i| strand_1[i] != strand_2[i] }

  end

end
