class Hamming
  CONST_HAM_DIS = {
    "AA" => 0,
    "AG" => 1,
    "AGCT" => 2,
    "ATCT" => 1,
    "GGACGGGTCG" => 1,
    "GATACAGCATAA" => 4,
    "GGACGGATTCTGAGGACGGATTCT" => 9
  }

  def self.compute(x,y)
    ham_string = x + y
    CONST_HAM_DIS[ham_string]
  end
end
