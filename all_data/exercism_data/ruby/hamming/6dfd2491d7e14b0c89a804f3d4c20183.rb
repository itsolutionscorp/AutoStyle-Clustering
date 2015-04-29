class Hamming

  def self.compute(strand1,strand2)
    @count = 0
    @st1 = strand1.chars
    @st2 = strand2.chars

    until @st1.empty? || @st2.empty? do
      if @st1[0].eql?(@st2[0])
        self.clear_compared
      else
        @count += 1
        self.clear_compared
      end
    end
    count = @count
  end

  def self.clear_compared
    @st1.shift
    @st2.shift
  end

end
