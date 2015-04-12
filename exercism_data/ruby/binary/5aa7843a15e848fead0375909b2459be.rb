class Binary

  attr_reader :rev_bin
  def initialize(binary=0)
    @rev_bin = binary.chars.reverse
  end

  def to_decimal
    (0..rev_bin.size-1).inject(0) do |decimal, ndx|
      return 0 unless (['0','1'].include? rev_bin[ndx])
      val = rev_bin[ndx].to_i
      decimal += val*(2**ndx)
    end
  end

end