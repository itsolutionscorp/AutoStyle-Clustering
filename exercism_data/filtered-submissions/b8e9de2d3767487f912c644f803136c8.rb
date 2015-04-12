class Hamming
  def compute(fst, snd)
    fst.chars
      .zip(snd.chars)
        .delete_if {|(_, r)| r.nil?}
          .keep_if {|(l, r)| l != r}
          .count
  end
end
