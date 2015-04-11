class Hamming
  class << self
    def compute(strand1, strand2)
      strand1.chars.zip(strand2.chars).count { | s1, s2 | different_elements?(s1, s2) }
    end

    private
      def different_elements?(s1, s2)
        !s1.nil? && !s2.nil? && s1 != s2
      end
  end

end
