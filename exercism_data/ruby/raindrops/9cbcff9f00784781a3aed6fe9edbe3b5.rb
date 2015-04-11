module Raindrops
  def self.convert candidate_number
    stringify the_factors_of candidate_number
  end

  def self.the_factors_of integer
    (1..integer/2).select {|n| integer % n == 0 } << integer
  end

  def self.stringify integer_array
    if (integer_array & [3, 5, 7]).any?
      "#{integer_array.include?(3) ? 'Pling' : ''}"\
      "#{integer_array.include?(5) ? 'Plang' : ''}"\
      "#{integer_array.include?(7) ? 'Plong' : ''}"
    else
      integer_array.last.to_s
    end
  end
end
