def compute(lstrand, rstrand)
    rstrand.chars.
      zip(lstrand.chars).
      reject{|(a,b)| not a or not b }.
      reject{|(a,b)| a == b }.
      length