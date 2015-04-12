module Hamming
  
  def compute d1, d2
    (0..d1.length).count do |i|
      d1[i] != d2[i]
    end
  end
end
