module Hamming

  def compute(lstrand, rstrand)
    rstrand.chars.
      zip(lstrand.chars).
      reject{|(a,b)| not a or not b }.
      reject{|(a,b)| a == b }.
      length
  end
  module_function :compute

end
