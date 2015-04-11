class Hamming

  def self.compute(strand1, strand2)
    def self.base_distance(base1, base2)
      def self.both_exists?(base1, base2)
        not [base1, base2].any? {|base| base.nil?}
      end

      if both_exists?(base1, base2) and base1 != base2
        1 
      else 
        0
      end
    end

    distance = 0

    strand1.chars.zip(strand2.chars).each do |base1, base2| 
        distance += base_distance(base1, base2)
    end

    distance
  end

end
