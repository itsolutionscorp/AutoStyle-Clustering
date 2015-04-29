class Hamming
  def self.compute(fst, snd)
    fst.split(//)
      .zip(snd.split(//))
        .delete_if {|_pair| _pair.last.nil?}
          .keep_if {|_pair| _pair.first != _pair.last}
          .count
  end
end
