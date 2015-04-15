class DNA < String
  def to_rna
    gsub(/[UT]/, {'U' => 'T', 'T' => 'U'})
  end
end
